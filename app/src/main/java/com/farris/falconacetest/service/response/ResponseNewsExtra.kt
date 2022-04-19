package com.farris.falconacetest.service.response

import com.google.gson.annotations.SerializedName

data class ResponseNewsExtra(

    @SerializedName("created")
    val created: Long? = null,

    @SerializedName("description")
    val description: String? = null
)
