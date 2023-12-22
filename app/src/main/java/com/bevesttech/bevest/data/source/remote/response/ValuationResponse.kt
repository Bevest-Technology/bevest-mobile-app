package com.bevesttech.bevest.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ValuationResponse (
    @field:SerializedName("valuation")
    val valuation: Int? = null
)