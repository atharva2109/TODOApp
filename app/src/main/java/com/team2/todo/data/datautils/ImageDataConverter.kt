package com.team2.todo.data.datautils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.room.TypeConverter
import java.io.ByteArrayOutputStream

class ImageDataConverter {

    @TypeConverter
    fun fromBitmapToByteArray(bitmap: Bitmap): ByteArray {
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, ByteArrayOutputStream())
        return ByteArrayOutputStream().toByteArray()
    }

    @TypeConverter
    fun fromByteArrayToBitmap(byteArr: ByteArray): Bitmap {
        return BitmapFactory.decodeByteArray(byteArr,0,byteArr.size)
    }

}