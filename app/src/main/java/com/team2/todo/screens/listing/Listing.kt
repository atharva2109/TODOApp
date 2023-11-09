package com.team2.todo.screens.listing

import android.annotation.SuppressLint
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.team2.todo.R
import com.team2.todo.screens.listing.ui_components.completed_sale.CompletedSaleList
import com.team2.todo.screens.listing.ui_components.in_sale.InSaleList
import com.team2.todo.ui.theme.PrimaryColor


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Listing() {
    var currentPage by remember { mutableIntStateOf(0) }
    Scaffold { it ->
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
            ) {
                if (currentPage == 0) {
                    InSaleList()
                } else {
                    CompletedSaleList()
                }
            }
            NavigationBar(contentColor = PrimaryColor ) {
                NavigationBarItem(
                    selected = currentPage == 0,
                    onClick = { currentPage = 0 },
                    label = { Text(text = "In Sale") },
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_todo_list_icon),
                            contentDescription = "TODO",
                            modifier = Modifier.size(27.dp)
                        )
                    },
                )
                NavigationBarItem(
                    selected = currentPage == 1,
                    onClick = { currentPage = 1 },
                    label = { Text(text = "Complete ") },
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_completed_list_icon),
                            contentDescription = "TODO",
                            modifier = Modifier.size(27.dp)
                        )
                    },
                )
            }
        }
    }
}