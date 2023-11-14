package com.team2.todo.common_ui_components
import android.graphics.ImageDecoder
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import kotlinx.coroutines.launch

@Composable
fun ImageLoader(uris: List<String>) {
    var currentImageIndex by remember { mutableIntStateOf(0) }
    val coroutineScope = rememberCoroutineScope()

    Column(modifier = Modifier.fillMaxSize()) {

        Box(
            modifier = Modifier
                .weight(1f)
                .height(100.dp)
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                itemsIndexed(uris) { index, uri ->
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.onPrimary,
                        ),
                        modifier = Modifier
                            .height(200.dp)
                            .width(300.dp)
                            .clickable {
                                if (index != currentImageIndex) {
                                    coroutineScope.launch {
                                        currentImageIndex = index
                                    }
                                }
                            }, elevation = CardDefaults.cardElevation(
                            defaultElevation = 6.dp,
                        )
                    ) {


                        Image(
                            bitmap = ImageDecoder.decodeBitmap(
                                ImageDecoder.createSource(
                                    LocalContext.current.contentResolver,
                                    uri.toUri()
                                )
                            ).asImageBitmap(),
                            contentDescription = "",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Fit
                        )
                    }
                }

            }

        }
    }
}
