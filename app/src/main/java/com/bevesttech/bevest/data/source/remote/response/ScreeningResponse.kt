package com.bevesttech.bevest.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ScreeningResponse(
    @field:SerializedName("Label:")
    val label: String? = null,

    @field:SerializedName("Screening Result:")
    val screeningResult: List<List<Any?>?>? = null
)