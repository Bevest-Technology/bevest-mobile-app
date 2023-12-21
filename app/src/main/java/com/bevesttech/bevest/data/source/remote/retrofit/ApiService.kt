package com.bevesttech.bevest.data.source.remote.retrofit

import com.bevesttech.bevest.data.source.remote.response.ScreeningResponse
import com.google.gson.annotations.JsonAdapter
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {
    @POST("screening")
    suspend fun screening(
        @Body body: RequestBody
    ): Response<ScreeningResponse>
}