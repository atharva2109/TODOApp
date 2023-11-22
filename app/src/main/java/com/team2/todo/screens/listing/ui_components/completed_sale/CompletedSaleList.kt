package com.team2.todo.screens.listing.ui_components.completed_sale

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.team2.todo.R
import com.team2.todo.common_ui_components.EmptyList
import com.team2.todo.data.entities.SubTodo
import com.team2.todo.data.entities.Todo
import com.team2.todo.screens.listing.ui_components.CustomListItem
import com.team2.todo.screens.listing.view_model.PropertyListViewModel

/**
 * Created by Manu KJ on 11/6/23.
 */
@Composable
fun CompletedSaleList(viewModel: PropertyListViewModel) {
    val list by remember { viewModel.completedPropertyList }.collectAsState()

    if (list.isNullOrEmpty()) {
        EmptyList(title = "Sale List Empty", drawableID = R.drawable.ic_no_completed_list)
    } else {
        Scaffold {
            LazyColumn(
                modifier = Modifier
                    .padding(it)
                    .padding(top = 10.dp)
            ) {
                items(list.size) { index ->
                    val todo = list[index]
                    CustomListItem(
                        property = todo,
                        onClearTaskClicked = {
                            viewModel.updateStatus(todo.todo.todoId, false)
                        },
                        onPermanentDelete = {
                            viewModel.deleteTheProperty(todo.todo.todoId)
                        }
                    )
                }
            }
        }
    }

}