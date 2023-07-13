package com.yumtaufikhidayat.xnews.model


import com.google.gson.annotations.SerializedName

data class NewsResponse(
    @SerializedName("articles")
    val articles: List<Article> = listOf(),
    @SerializedName("status")
    val status: String = "", // ok
    @SerializedName("totalResults")
    val totalResults: Int = 0 // 36
)