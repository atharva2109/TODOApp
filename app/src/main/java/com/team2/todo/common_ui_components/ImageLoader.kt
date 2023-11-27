package com.team2.todo.common_ui_components

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import androidx.compose.material.icons.Icons
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import kotlinx.coroutines.launch

@Composable
fun ImageLoader(bitmapList: List<Bitmap?>) {
    var currentImageIndex by remember { mutableIntStateOf(0) }
    val coroutineScope = rememberCoroutineScope()

    Column() {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)


        ) {
            LazyRow(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center
            ) {
                itemsIndexed(bitmapList) { index, bitmap ->
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.onPrimary,
                        ), modifier = Modifier.clickable {
                                if (index != currentImageIndex) {
                                    coroutineScope.launch {
                                        currentImageIndex = index
                                    }
                                }
                            }, elevation = CardDefaults.cardElevation(
                            defaultElevation = 6.dp,
                        )
                    ) {


                        if (bitmap != null) {
                            Image(
                                bitmap = bitmap.asImageBitmap(),
                                contentDescription = "",
                                modifier = Modifier
                                    .fillMaxWidth(.7f)
                                    .height(250.dp),
                                contentScale = ContentScale.Fit
                            )
                        }

                        // Delete icon functionality

                    }
                }

            }

        }
    }
}

