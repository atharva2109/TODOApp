package com.team2.todo.screens.subtodo_details.ui_components

import android.graphics.ImageDecoder
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import com.team2.todo.R

@Composable
fun DisplaySubTodoImage(imagePathUri: String?) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .background(color = Color.White, shape = RoundedCornerShape(16.dp)),
            contentAlignment = Alignment.Center,
        ) {

            if (imagePathUri != null) {
                Image(
                    bitmap = ImageDecoder.decodeBitmap(
                        ImageDecoder.createSource(
                            LocalContext.current.contentResolver, imagePathUri.toUri()
                        )
                    ).asImageBitmap(),
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxWidth(.7f)
                        .height(150.dp),
                    contentScale = ContentScale.Fit
                )
            }

        }
    }
}
