package com.yumtaufikhidayat.xnews.utils

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.Random
import java.util.concurrent.TimeUnit

private var vibrantLightColorList = arrayOf(
    ColorDrawable(Color.parseColor("#FFEEAD")),
    ColorDrawable(Color.parseColor("#93CFB3")),
    ColorDrawable(Color.parseColor("#FD7A7A")),
    ColorDrawable(Color.parseColor("#FACA5F")),
    ColorDrawable(Color.parseColor("#1BA798")),
    ColorDrawable(Color.parseColor("#6AA9AE")),
    ColorDrawable(Color.parseColor("#FFBF27")),
    ColorDrawable(Color.parseColor("#D93947"))
)

private fun getRandomDrawableColor(): ColorDrawable {
    val idx: Int = Random().nextInt(vibrantLightColorList.size)
    return vibrantLightColorList[idx]
}

fun ImageView.loadImage(url: String?) {
    Glide.with(this.context)
        .load(url)
        .placeholder(getRandomDrawableColor())
        .error(getRandomDrawableColor())
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(this)
}

fun covertDateToTime(dataDate: String?): String? {
    var convTime: String? = null
    val suffix = "ago"
    try {
        val dateFormat = SimpleDateFormat(Constants.DATE_FORMAT, Locale.getDefault())
        val pasTime = dataDate?.let { dateFormat.parse(it) }
        val nowTime = Date()
        val dateDiff = nowTime.time - (pasTime?.time ?: 0)
        val second: Long = TimeUnit.MILLISECONDS.toSeconds(dateDiff)
        val minute: Long = TimeUnit.MILLISECONDS.toMinutes(dateDiff)
        val hour: Long = TimeUnit.MILLISECONDS.toHours(dateDiff)
        val day: Long = TimeUnit.MILLISECONDS.toDays(dateDiff)

        when {
            second < 60 -> convTime = "$second second(s) $suffix"
            minute < 60 -> convTime = "$minute minute(s) $suffix"
            hour < 24 -> convTime = "$hour hour(s) $suffix"
            day >= 7 -> {
                convTime = if (day > 360) {
                    "${day/360} year(s) $suffix"
                } else if (day > 30) {
                    "${day/36} month(s) $suffix"
                } else {
                    "${day/7} weak(s) $suffix"
                }
            }
            day < 7 -> convTime = "$day day(s) $suffix"
        }
    } catch (e: ParseException) {
        e.printStackTrace()
    } catch (e: Exception) {
        e.printStackTrace()
    }

    return convTime
}