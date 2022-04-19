package com.farris.falconacetest.service.response

import com.google.gson.annotations.SerializedName

data class ResponseNewsItem(

    @SerializedName("type")
    val type: String? = null,

    @SerializedName("title")
    val title: String? = null,

    @SerializedName("source")
    val source: String? = null,

    @SerializedName("ref")
    val ref: String? = null,

    @SerializedName("style")
    val style: String? = null,

    @SerializedName("appearance")
    val appearance: ResponseNewsAppearance? = null,

    @SerializedName("extra")
    val extra: ResponseNewsExtra? = null,

    @SerializedName("_meta")
    val meta: ResponseNewsMeta? = null,

    @SerializedName("items")
    val items: List<ResponseNewsItem>? = null
)
