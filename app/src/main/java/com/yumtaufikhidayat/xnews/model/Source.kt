package com.yumtaufikhidayat.xnews.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Source(
    @SerializedName("id")
    val id: String = "", // reuters
    @SerializedName("name")
    val name: String = "" // Yahoo Entertainment
): Parcelable