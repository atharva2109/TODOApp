package com.team2.todo.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.team2.todo.R
import com.team2.todo.common_ui_components.ReminderAlertCompose
import com.team2.todo.screens.listing.ui_components.BottomNavigationCompose
import com.team2.todo.screens.listing.ui_components.completed_sale.CompletedSaleList
import com.team2.todo.screens.listing.ui_components.in_sale.InSaleList
import com.team2.todo.ui.theme.PrimaryColor


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    var currentPage by remember { mutableIntStateOf(0) }
    var showReminderAlert by remember { mutableStateOf(true) }
    MaterialTheme(typography = Typography()) {
        Scaffold { it ->
            if (showReminderAlert) {
                ModalBottomSheet(onDismissRequest = { showReminderAlert = false; }) {
                    ReminderAlertCompose()
                }
            }
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
                BottomNavigationCompose(
                    currentPage = currentPage,
                    onClick = { nextPage -> currentPage = nextPage })
            }
        }
    }
}