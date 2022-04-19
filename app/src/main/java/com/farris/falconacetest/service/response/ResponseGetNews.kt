package com.farris.falconacetest.service.response

import com.google.gson.annotations.SerializedName

data class ResponseGetNews(

    @SerializedName("getVector")
    val vector: ResponseVector? = null
)
