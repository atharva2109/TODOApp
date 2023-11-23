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
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ElevatedButton
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
import com.team2.todo.ui.theme.BlueColor
import com.team2.todo.ui.theme.PrimaryColor
import com.team2.todo.utils.PermissionUtil


@Composable
fun PickImageForSubTodo(bitmapCallback: (Bitmap) -> Unit) {
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    val context = LocalContext.current
    var bitmap by remember { mutableStateOf<Bitmap?>(null) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            imageUri = uri
            // Load the bitmap from the selected URI
            bitmap = if (Build.VERSION.SDK_INT < 28) {
                MediaStore.Images.Media.getBitmap(context.contentResolver, uri)
            } else {
                val source = ImageDecoder.createSource(context.contentResolver, uri)
                ImageDecoder.decodeBitmap(source)
            }
        }
    }


    Box(
        modifier = Modifier
            .border(
                2.dp,
                BlueColor,
                shape = RoundedCornerShape(8.dp)
            )
            .clickable {
                launcher.launch("image/*")
            }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(5.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_gallery),
                contentDescription = "loading",
                modifier = Modifier.height(150.dp)
            )
            Spacer(modifier = Modifier.width(26.dp))
            Text(text = "Gallery")
        }

    }


    if (imageUri == null) {

        bitmap?.let { bitmapCallback(it) }

    }
}
