package com.hyperdesign.delivaryUser.feature.trackorder.ui.utilies

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.model.LatLng

fun Context.drawableToBitmap(@DrawableRes drawableId: Int): Bitmap {
    val drawable = ContextCompat.getDrawable(this, drawableId)
        ?: throw IllegalArgumentException("Drawable not found")

    val bitmap = Bitmap.createBitmap(
        drawable.intrinsicWidth, drawable.intrinsicHeight, Bitmap.Config.ARGB_8888
    )
    val canvas = Canvas(bitmap)
    drawable.setBounds(0, 0, canvas.width, canvas.height)
    drawable.draw(canvas)

    return bitmap
}

fun parseLatLng(latitude: String, longitude: String): LatLng? {
    val lat = latitude.toDoubleOrNull() ?: return null
    val lng = longitude.toDoubleOrNull() ?: return null

    if (lat == 0.0 && lng == 0.0) return null
    if (lat < -90.0 || lat > 90.0) return null
    if (lng < -180.0 || lng > 180.0) return null

    return LatLng(lat, lng)
}