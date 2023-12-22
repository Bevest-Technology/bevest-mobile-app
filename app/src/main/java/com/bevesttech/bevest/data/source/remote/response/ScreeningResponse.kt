package com.bevesttech.bevest.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ScreeningResponse(
    @field:SerializedName("label")
    val label: String? = null,
)