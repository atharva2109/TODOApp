package com.team2.todo.screens.add_todo.ui_components

import androidx.compose.runtime.Composable
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.team2.todo.R
import com.team2.todo.ui.theme.PrimaryColor
import java.io.ByteArrayOutputStream
import java.lang.Integer.min

@Composable
fun PickImageFromGallery(activity: ComponentActivity) {
    var imageUris by remember {
        mutableStateOf<List<Uri>>(emptyList())
    }
    val context = LocalContext.current
    val bitmaps = remember {
        //Bitmap is similar to a pixel of an image
        mutableStateOf<List<Bitmap>>(emptyList())
    }

    //rememberLauncherForActivityResult-:This function is part of the activity or fragment composition lifecycle in Jetpack Compose.
    // It creates a launcher that is remembered across recompositions. The launcher is used to start an activity for result.
    val launcher = rememberLauncherForActivityResult(
        contract =
        //This contract is typically used for actions that require obtaining multiple content (in this case, multiple images).
        ActivityResultContracts.GetMultipleContents()
    ) { uris: List<Uri>? ->
        uris?.let {
            imageUris = it
            bitmaps.value =
                it.map { uri ->   //converts each uri to a bitmap, stores the list of Bitmaps in a MutableState variable named bitmaps

                    //Depending on the Android SDK version, it uses different methods to retrieve the Bitmap from the Uri.
                    // For versions below 28, it uses MediaStore.Images.Media.getBitmap. For version 28 and above, it uses the ImageDecoder class to decode the bitmap from the Uri.
                    // This is necessary due to changes in how images are loaded and decoded in newer Android versions.
                    if (Build.VERSION.SDK_INT < 28) {
                        MediaStore.Images.Media.getBitmap(context.contentResolver, uri)
                    } else {
                        val source = ImageDecoder.createSource(context.contentResolver, uri)
                        ImageDecoder.decodeBitmap(source)
                    }
                }
        }
    }

    var startIndex by remember { mutableStateOf(0) }

    if (bitmaps.value.isEmpty()) {
        Box(
            modifier = Modifier.clickable {
                launcher.launch("image/*")
            }
        ) {
            UploadImagePlaceHolder()
        }
    } else {
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(
                modifier = Modifier.width(20.dp),
                onClick = {
                    // Handle backward arrow click
                    startIndex -= 3
                }
            ) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Backward")
            }

            Row(
                modifier = Modifier
                    .width(300.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                // Use LazyRow instead of Row for horizontal scrolling
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 2.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier
                        .width(300.dp)
                        .height(100.dp)

                    // Set the fixed height of the carousel
                ) {
                    // Display three images at a time based on the visible range
                    itemsIndexed(
                        bitmaps.value.subList(
                            startIndex,
                            min(startIndex + 3, bitmaps.value.size)
                        )
                    ) { index, bitmap ->
                        Box(
                            modifier = Modifier
                                .width(100.dp) // Set the fixed width of each Box
                                .aspectRatio(1f)
                                .clip(MaterialTheme.shapes.medium)
                        ) {
                            val byteArrayOutputStream = ByteArrayOutputStream()
                            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
                            val byteArray = byteArrayOutputStream.toByteArray()

                            // Convert the byte array to a base64-encoded string
                            val base64String = Base64.encodeToString(byteArray, Base64.DEFAULT)

                            // Log the base64-encoded string
                            Log.d("BitmapLog", base64String)
                            Image(
                                bitmap = bitmap.asImageBitmap(),
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxSize()
                            )

                            // Delete icon functionality
                            IconButton(
                                modifier = Modifier
                                    .align(Alignment.TopEnd)
                                    .padding(4.dp),
                                onClick = {
                                    // Remove the clicked image
                                    val globalIndex = index + startIndex
                                    imageUris =
                                        imageUris.toMutableList().apply { removeAt(globalIndex) }
                                    bitmaps.value =
                                        bitmaps.value.toMutableList()
                                            .apply { removeAt(globalIndex) }
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = "Delete",
                                    tint = Color.Red
                                )
                            }
                        }
                    }
                }

            }
            IconButton(
                modifier = Modifier.width(20.dp),
                onClick = {
                    // Handle forward arrow click
                    startIndex += 3
                }
            ) {
                Icon(imageVector = Icons.Default.ArrowForward, contentDescription = "Forward")
            }

        }
    }
}


@Composable
fun UploadImagePlaceHolder() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
    ) {
        // Text label
        Text(
            text = "Upload Image",
            modifier = Modifier.padding(bottom = 10.dp),
            fontWeight = FontWeight.SemiBold,
            color = PrimaryColor,
            fontSize = 18.sp
        )
        // Box with dotted border, center icon, and text
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .background(color = Color.White, shape = RoundedCornerShape(8.dp))
                .border(1.dp, color = PrimaryColor, shape = RoundedCornerShape(8.dp)),
            contentAlignment = Alignment.Center,
        ) {
            // Icon in the center
            Image(
                painter = painterResource(id = R.drawable.image_upload_placeholder),
                contentDescription = "Upload Image",
            )
        }
    }
}
