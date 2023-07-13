package com.yumtaufikhidayat.xnews.model


import com.google.gson.annotations.SerializedName

data class Source(
    @SerializedName("id")
    val id: String = "", // reuters
    @SerializedName("name")
    val name: String = "" // Yahoo Entertainment
)