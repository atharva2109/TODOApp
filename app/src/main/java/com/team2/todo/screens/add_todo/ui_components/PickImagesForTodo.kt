package com.team2.todo.screens.add_todo.ui_components

import android.Manifest
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.team2.todo.R
import com.team2.todo.ui.theme.PrimaryColor
import com.team2.todo.utils.LocationUtils
import com.team2.todo.utils.PermissionUtil
import com.team2.todo.utils.PermissionUtil.checkAndRequestLocationPermissions

/**
 * Created by Atharva K on 11/14/23.
 */

@Composable

fun PickImagesForTodo(activity: ComponentActivity):List<Uri> {

    val context = LocalContext.current
    var imageUris by remember {
        mutableStateOf<List<Uri>>(emptyList())
    }

    val bitmaps = remember {
        //Bitmap is similar to a pixel of an image
        mutableStateOf<List<Bitmap>>(emptyList())
    }
    val launcher = rememberLauncherForActivityResult(
        contract =
        ActivityResultContracts.GetMultipleContents()
    ) { uris: List<Uri>? ->
        uris?.let {
            imageUris = it
            bitmaps.value =
                it.map { uri ->
                    if (Build.VERSION.SDK_INT < 28) {
                        MediaStore.Images.Media.getBitmap(context.contentResolver, uri)
                    } else {
                        val source = ImageDecoder.createSource(context.contentResolver, uri)
                        ImageDecoder.decodeBitmap(source)
                    }
                }
        }
    }
    val launchImagePickerPermission = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissionsMap ->
        val areGranted = permissionsMap.values.reduce { acc, next -> acc || next }
        if (areGranted) {
            launcher.launch("image/*")
        } else {
            PermissionUtil.showToastAndLaunchSetting(
                context,
                "Please Enable Read External Storage permission"
            )
        }
    }


    var startIndex by remember { mutableStateOf(0) }

    if (bitmaps.value.isEmpty()) {
        Box(
            modifier = Modifier.clickable {
                checkAndRequestLocationPermissions(
                    context,
                    arrayOf(Manifest.permission.READ_MEDIA_IMAGES),
                    launchImagePickerPermission
                ) {
                    launcher.launch("image/*")
                }
            }
        ) {
            UploadImagePlaceHolder()
        }
    } else {
        Row(verticalAlignment = Alignment.CenterVertically) {


            // Use LazyRow instead of Row for horizontal scrolling
            LazyRow(
                contentPadding = PaddingValues(horizontal = 1.dp),

                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .padding(vertical = 40.dp)


            ) {
                itemsIndexed(
                    bitmaps.value.subList(
                        0,
                        bitmaps.value.size
                    )
                ) { index, bitmap ->

                    Box(
                        modifier = Modifier
                            .width(300.dp) // Set the fixed width of each Box
                            .height(600.dp)

                    ) {
                        Image(
                            bitmap = bitmap.asImageBitmap(),
                            contentDescription = null,
                            contentScale = ContentScale.FillBounds,
                            modifier = Modifier
                                .fillMaxHeight()
                                .width(250.dp)
                        )

                        // Delete icon functionality
                        IconButton(
                            modifier = Modifier
                                .align(Alignment.TopStart)
                                .padding(start = 210.dp, top = 0.dp),
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


    }
    return imageUris
}


@Composable
fun UploadImagePlaceHolder() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
    ) {
        Text(
            text = "Upload Image",
            modifier = Modifier.padding(bottom = 10.dp),
            fontWeight = FontWeight.SemiBold,
            color = PrimaryColor,
            fontSize = 18.sp
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .background(color = Color.White, shape = RoundedCornerShape(8.dp))
                .border(1.dp, color = PrimaryColor, shape = RoundedCornerShape(8.dp)),
            contentAlignment = Alignment.Center,
        ) {

            Image(
                painter = painterResource(id = R.drawable.image_upload_placeholder),
                contentDescription = "Upload Image",
                modifier = Modifier
                    .height(150.dp)
                    .fillMaxWidth(.6f)

            )
        }
    }
}
