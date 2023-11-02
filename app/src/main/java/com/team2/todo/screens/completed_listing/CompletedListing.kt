@file:OptIn(ExperimentalMaterial3Api::class)

package com.team2.todo.screens.completed_listing

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.team2.todo.screens.completed_listing.ui_components.DummyCompose

@Composable
fun CompletedListing() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Completed APP Bar")
                }
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .padding(it)
            ) {
                DummyCompose(text = "sdsds")
            }

        }
    )
}