package com.farris.falconacetest.service.response

import com.google.gson.annotations.SerializedName

data class ResponseVector(
    @SerializedName("items")
    val items: List<ResponseNewsItem>? = null
)
