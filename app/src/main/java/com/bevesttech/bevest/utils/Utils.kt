package com.bevesttech.bevest.utils

import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.OpenableColumns
import android.util.Log
import android.util.Patterns
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import com.bevesttech.bevest.data.Result
import java.text.NumberFormat

enum class Role { BUSINESS, INVESTOR, NONE }
enum class Onboarding { FINISH, ACTIVE }
enum class BusinessValuationState { WAITING, PROCESS, REJECTED, ACCEPTED, COMPLETED }

object Utils {
    private const val FILENAME_FORMAT = "yyyyMMdd_HHmmss"
    private val timeStamp: String = SimpleDateFormat(FILENAME_FORMAT, Locale.US).format(Date())
    fun isValidEmail(email: String): Boolean =
        email.contains('@') && email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()

    fun isValidPassword(password: String): Boolean {
        return password.length >= 8
    }

    fun uriToFile(imageUri: Uri, context: Context): File {
        val myFile = createCustomTempFile(context)
        val inputStream = context.contentResolver.openInputStream(imageUri) as InputStream
        val outputStream = FileOutputStream(myFile)
        val buffer = ByteArray(1024)
        var length: Int
        while (inputStream.read(buffer).also { length = it } > 0) outputStream.write(
            buffer,
            0,
            length
        )
        outputStream.close()
        inputStream.close()
        return myFile
    }

    fun createCustomTempFile(context: Context): File {
        val filesDir = context.externalCacheDir
        return File.createTempFile(timeStamp, ".jpg", filesDir)
    }

    fun getFileName(uri: Uri, context: Context): String {
        var fileName: String? = null
        if (uri.scheme.equals("content")) {
            val cursor: Cursor? = context.contentResolver.query(uri, null, null, null, null)
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    fileName = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME))
                }
            } catch (e: Exception) {
                Log.e("Utils", "getFileName: ${e.message.toString()}")
            } finally {
                cursor?.close()
            }
            if (fileName == null) {
                fileName = uri.path
                val cutt: Int = fileName!!.lastIndexOf('/')
                if (cutt != -1) {
                    fileName = fileName.substring(cutt + 1)
                }
            }
        }
        return fileName ?: uri.toString()
    }

    fun isBusiness(role: String): Boolean {
        return role == Role.BUSINESS.name
    }

    suspend fun <T> safeApiCall(apiToBeCalled: suspend() -> Response<T>): Result<T> {
        return withContext(Dispatchers.IO) {
            try {
                val response: Response<T> = apiToBeCalled()

                Log.d("Utils", "safeApiCall: ${response.body()}")
                if (response.isSuccessful) {
                    Result.Success(data = response.body()!!)
                } else {
                    Result.Error(error =  "Something went wrong")
                }
            } catch (e: HttpException) {
                Result.Error(error = e.message ?: "Something went wrong")
            } catch (e: IOException) {
                Result.Error(error = "Please check your network connection")
            } catch (e: Exception) {
                Result.Error(error = "Something went wrong")
            }
        }
    }

    fun String.toIDRCurrenty(): String {
        val localeID = Locale("in", "ID")
        val doubleValue = this.toDoubleOrNull() ?: return this
        val numberFormat = NumberFormat.getCurrencyInstance(localeID)
        numberFormat.minimumFractionDigits = 0
        return numberFormat.format(doubleValue)
    }
}