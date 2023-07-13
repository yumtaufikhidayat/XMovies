package com.yumtaufikhidayat.xnews.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Article(
    @SerializedName("author")
    val author: String? = "", // Yahoo Sports Staff
    @SerializedName("content")
    val content: String? = "", // Elias Díaz of the Colorado Rockies was a surprise hero for the National League on Tuesday, hitting a two-run home run in the eighth inning of the 2023 MLB All-Star Game in Seattle. (AP Photo/Lindsey … [+6984 chars]
    @SerializedName("description")
    val description: String? = "", // The AL was leading 2-1 heading into the eighth inning Tuesday.
    @SerializedName("publishedAt")
    val publishedAt: String? = "", // 2023-07-12T02:30:00Z
    @SerializedName("source")
    val source: Source? = Source(),
    @SerializedName("title")
    val title: String? = "", // 2023 MLB All-Star Game: American League ahead 2-1 after sac fly in the sixth, Yandy Díaz homer in the second - Yahoo Sports
    @SerializedName("url")
    val url: String? = "", // https://sports.yahoo.com/2023-mlb-all-star-game-national-league-on-top-3-2-after-elias-diaz-two-run-homer-024646924.html
    @SerializedName("urlToImage")
    val urlToImage: String? = "" // https://s.yimg.com/ny/api/res/1.2/G57tHrz_9YmPT3etChV.wg--/YXBwaWQ9aGlnaGxhbmRlcjt3PTEyMDA7aD04MDA-/https://s.yimg.com/os/creatr-uploaded-images/2023-07/99603640-2061-11ee-9f6b-68ab6a9a8be3
): Parcelable