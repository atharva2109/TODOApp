package com.team2.todo.screens.listing

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.team2.todo.screens.completed_listing.ui_components.DummyCompose
import com.team2.todo.utils.NavigationUtil
import com.team2.todo.utils.NotificationUtil
import com.team2.todo.utils.Screen

@Composable
fun Listing() {
    Column {
//        var service = NotificationService(this);
        DummyCompose(text = "Listing Screen");
        Button(onClick = {
            NavigationUtil.navigateTo(Screen.CompletedListing)
//            service.showBasicNotification();
        }) {
            Text(text = "Go to Complete Screen")
        }
        Button(onClick = {
            NotificationUtil.showGeoFencingNotification()
//            service.showBasicNotification();
        }) {
            Text(text = "Show Notification")
        }
    }
}