package com.farris.falconacetest.service.response

import com.google.gson.annotations.SerializedName

data class ResponseNewsMeta(
    @SerializedName("section")
    val section: String? = null,

    @SerializedName("category")
    val category: List<String>? = null,
)
