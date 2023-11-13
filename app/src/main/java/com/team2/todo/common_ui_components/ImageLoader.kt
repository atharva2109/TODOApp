package com.team2.todo.common_ui_components

import android.graphics.Bitmap
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.team2.todo.utils.ImageBitmap

@Composable
fun ImageLoader(url: Uri) {
    val context = LocalContext.current

    var isLoading by remember {
        mutableStateOf(true)
    }
    var bitmap by remember {
        mutableStateOf<Bitmap?>(null)
    }

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)

        ) {

            if (bitmap == null && isLoading) {

                ImageBitmap.loadImagefromUri(
                    context = context,
                    uri = url!!,
                    onSuccess = { loadedBitmap ->
                        bitmap = loadedBitmap
                        isLoading = false

                    },
                    onError = {
                        isLoading = false

                    }
                )

            }
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.TopCenter
            ) {

                if (isLoading) {
                    CircularProgressIndicator()
                } else {
                    bitmap?.also {
                        Image(
                            bitmap = bitmap!!.asImageBitmap(),
                            contentDescription = "",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .width(300.dp)
                                .height(200.dp)
                        )
                    }
                }
            }

        }
    }
}
