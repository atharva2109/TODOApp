package com.team2.todo.common_ui_components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.BrowseGallery
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImageModeSelector(){
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }
    // Screen content

    ExtendedFloatingActionButton(
        text = { Text(text = "Upload Image(s)") },
        icon = { Icon(Icons.Filled.Add, contentDescription = "") },
        onClick = {
            showBottomSheet = true
        }
    )
    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = {
                showBottomSheet = false
            },
            sheetState = sheetState
        ) {

            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.Top) {
                // Sheet content
                ElevatedButton(onClick = {
                    scope.launch { sheetState.hide() }.invokeOnCompletion {
                        if (!sheetState.isVisible) {
                            showBottomSheet = false
                        }
                    }
                }) {

                    Icon(imageVector = Icons.Default.CameraAlt, contentDescription = null)
                    Spacer(modifier = Modifier.width(width = 8.dp))
                    Text(text = "Camera")
                    Spacer(modifier = Modifier.width(width = 4.dp))

                }
                ElevatedButton(onClick = {
                    scope.launch { sheetState.hide() }.invokeOnCompletion {
                        if (!sheetState.isVisible) {
                            showBottomSheet = false
                        }
                    }
                }) {

                    Icon(imageVector = Icons.Default.BrowseGallery, contentDescription = null)
                    Spacer(modifier = Modifier.width(width = 8.dp))
                    Text(text = "Gallery")
                    Spacer(modifier = Modifier.width(width = 4.dp))

                }


            }

            Spacer(modifier = Modifier.height(30.dp))
        }
    }
}