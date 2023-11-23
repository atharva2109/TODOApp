package com.team2.todo.screens.listing.ui_components.in_sale

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.team2.todo.R
import com.team2.todo.common_ui_components.EmptyList
import com.team2.todo.screens.listing.view_model.PropertyListViewModel
import com.team2.todo.data.entities.SubTodo
import com.team2.todo.data.entities.Todo
import com.team2.todo.screens.listing.ui_components.CustomListItem
import java.time.LocalDateTime
import com.team2.todo.data.entities.relations.TodoWithSubTodos as TodoWithSubTodos1

/**
 * Created by Manu KJ on 11/6/23.
 */

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InSaleList(viewModel: PropertyListViewModel) {

    val list by remember { viewModel.inSalePropertyList }.collectAsState()
    val ctxt = LocalContext.current;

    if (list.isNullOrEmpty()) {
        EmptyList(title = "No Active Sales Found", drawableID = R.drawable.ic_no_in_sale_list)
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
                            viewModel.updateStatus(todo.todo.todoId, true)
                        },
                    )
                }
            }
        }
    }
}


fun getDummyData(): List<TodoWithSubTodos1> {

    var subTaskList = mutableListOf<SubTodo>();


    subTaskList.add(
        SubTodo(
            2,
            1,
            "SubTask2",
            "Inspect Property",
            null,
            LocalDateTime.now(),
            LocalDateTime.now(),
            true,
            1
        )
    )
    // val todos by viewModel.todoListBasedOnId.collectAsState(initial = emptyList())
    var mainTask = TodoWithSubTodos1(
        todo = Todo(
            todoId = 1,
            title = "Main Task One",
            status = false,
            priority = 2,
            longitude = 54.0,
            latitude = 55.0,
            label = "Maintenance",
            dueDate = LocalDateTime.now(),
            createdDate = LocalDateTime.now(),
            description = "Property in Lahore",
            price = 10.0
        ), subtodos = subTaskList, images = emptyList()
    )
    var mainTaskOne = TodoWithSubTodos1(
        todo = Todo(
            todoId = 1,
            title = "Main Task One",
            status = false,
            priority = 1,
            longitude = 54.0,
            latitude = 55.0,
            label = "Maintenance",
            dueDate = LocalDateTime.now(),
            createdDate = LocalDateTime.now(),
            description = "Property in Lahore",
            price = 10.0
        ), subtodos = subTaskList, images = emptyList()
    )

    val list: MutableList<TodoWithSubTodos1> = mutableListOf()
    list.add(mainTask)
    list.add(mainTaskOne)
    return list;

}