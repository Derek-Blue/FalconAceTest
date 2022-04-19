package com.farris.falconacetest.service.response

import com.google.gson.annotations.SerializedName

data class ResponseNewsAppearance(
    @SerializedName("mainTitle")
    val mainTitle: String? = null,

    @SerializedName("subTitle")
    val subTitle: String? = null,

    @SerializedName("thumbnail")
    val thumbnail: String? = null,

    @SerializedName("subscript")
    val subscript: String? = null
)
