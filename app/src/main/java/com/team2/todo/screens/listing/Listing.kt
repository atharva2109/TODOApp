package com.team2.todo.screens.listing

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.team2.todo.screen_template.ui_components.DummyCompose
import com.team2.todo.utils.NavigationUtil
import com.team2.todo.utils.Screen

@Composable
fun Listing() {
    Column {
        DummyCompose(text = "Listing Screen");
        Button(onClick = {
            NavigationUtil.navigateTo(Screen.CompletedListing)
        }) {
            Text(text = "Go to Complete Screen")
        }
    }
}