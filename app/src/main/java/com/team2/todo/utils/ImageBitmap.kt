package com.team2.todo.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri

object ImageBitmap {

    fun loadImagefromUri(
        context: Context,
        uri: Uri,
        onSuccess: (Bitmap) -> Unit,
        onError: (String) -> Unit
    ) {
        try {
            val contentUriToFilePath = ContentURIToFilePath()
            val filePath = contentUriToFilePath.getFilePath(context, uri)
            val imageBitmap = BitmapFactory.decodeFile(filePath)
            if (imageBitmap != null) onSuccess(imageBitmap) else onError("An Error has occurred!!")

        } catch (e: Exception) {
            e.printStackTrace()
            onError("Error ${e.localizedMessage}")
        }

    }

}