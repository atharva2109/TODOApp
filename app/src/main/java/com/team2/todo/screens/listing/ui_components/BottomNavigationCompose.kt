package com.team2.todo.screens.listing.ui_components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.team2.todo.R
import com.team2.todo.ui.theme.PrimaryColor

/**
 * Created by Manu KJ on 11/9/23.
 */

@Composable
fun BottomNavigationCompose(currentPage: Int, onClick: (nextPage: Int) -> Unit){
    return NavigationBar(contentColor = PrimaryColor) {
        NavigationBarItem(
            selected = currentPage == 0,
            onClick = { onClick(0) },
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
            onClick = { onClick(1) },
            label = { Text(text = "Complete ") },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_completed_list_icon),
                    contentDescription = "TODO",
                    modifier = Modifier.size(27.dp)
                )
            },
        )
    };

}