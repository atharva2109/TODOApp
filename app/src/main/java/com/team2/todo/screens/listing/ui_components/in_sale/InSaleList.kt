package com.team2.todo.screens.listing.ui_components.in_sale

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.team2.todo.R
import com.team2.todo.common_ui_components.EmptyList
import com.team2.todo.screens.listing.view_model.PropertyListViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.team2.todo.data.RealEstateDatabase
import com.team2.todo.data.entities.SubTodo
import com.team2.todo.data.entities.Todo
import com.team2.todo.data.repo.TodoRepo
import com.team2.todo.screen_template.view_model.DummyViewModel
import com.team2.todo.screens.listing.ui_components.CustomListItem
import java.time.LocalDateTime
import com.team2.todo.data.entities.relations.TodoWithSubTodos as TodoWithSubTodos1

/**
 * Created by Manu KJ on 11/6/23.
 */

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InSaleList(viewModel: PropertyListViewModel = viewModel()) {
//    val list = viewModel.uncompletedPropertyList.value
    // For expand and collapse https://mrugendrathatte.medium.com/expandable-list-in-compose-b5ebdd768f37
    val todoContext = LocalContext.current
    val database = RealEstateDatabase.getInstance(todoContext)
    val todoRepo = TodoRepo(database)
    val viewModel = PropertyListViewModel(todoRepo)
    val list = getDummyData();

    if (list.isEmpty()) {
        EmptyList(title = "No Active Sales Found", drawableID = R.drawable.ic_no_in_sale_list)
    } else {
        Scaffold {
            TopAppBar(
                title =
                {
                    Text(text = "Active Todos")
                })

            Spacer(modifier = Modifier.height(40.dp))

            LazyColumn(
            ) {
                items(list.size) { index ->
                    val todo = list[index]
                    CustomListItem(property = todo,viewModel)
                }
            }

        }



    }
}


fun getDummyData(): List<TodoWithSubTodos1> {

    var subTaskList = mutableListOf<SubTodo>();

    subTaskList.add(
        SubTodo(
            1,
            1,
            "SubTask1",
            "Maintain Property",
            LocalDateTime.now(),
            LocalDateTime.now(),
            true,
            2
        )
    )
    subTaskList.add(
        SubTodo(
            2,
            1,
            "SubTask2",
            "Inspect Property",
            LocalDateTime.now(),
            LocalDateTime.now(),
            false,
            1
        )
    )
    subTaskList.add(
        SubTodo(
            1,
            1,
            "SubTask1",
            "Maintain Property",
            LocalDateTime.now(),
            LocalDateTime.now(),
            true,
            2
        )
    )
    subTaskList.add(
        SubTodo(
            2,
            1,
            "SubTask2",
            "Inspect Property",
            LocalDateTime.now(),
            LocalDateTime.now(),
            true,
            1
        )
    )
    subTaskList.add(
        SubTodo(
            1,
            1,
            "SubTask1",
            "Maintain Property",
            LocalDateTime.now(),
            LocalDateTime.now(),
            false,
            2
        )
    )
    subTaskList.add(
        SubTodo(
            2,
            1,
            "SubTask2",
            "Inspect Property",
            LocalDateTime.now(),
            LocalDateTime.now(),
            true,
            1
        )
    )
    subTaskList.add(
        SubTodo(
            1,
            1,
            "SubTask1",
            "Maintain Property",
            LocalDateTime.now(),
            LocalDateTime.now(),
            true,
            2
        )
    )
    subTaskList.add(
        SubTodo(
            2,
            1,
            "SubTask2",
            "Inspect Property",
            LocalDateTime.now(),
            LocalDateTime.now(),
            false,
            1
        )
    )
    subTaskList.add(
        SubTodo(
            1,
            1,
            "SubTask1",
            "Maintain Property",
            LocalDateTime.now(),
            LocalDateTime.now(),
            true,
            2
        )
    )
    subTaskList.add(
        SubTodo(
            2,
            1,
            "SubTask2",
            "Inspect Property",
            LocalDateTime.now(),
            LocalDateTime.now(),
            true,
            1
        )
    )
    subTaskList.add(
        SubTodo(
            1,
            1,
            "SubTask1",
            "Maintain Property",
            LocalDateTime.now(),
            LocalDateTime.now(),
            true,
            2
        )
    )
    subTaskList.add(
        SubTodo(
            2,
            1,
            "SubTask2",
            "Inspect Property",
            LocalDateTime.now(),
            LocalDateTime.now(),
            false,
            1
        )
    )
    subTaskList.add(
        SubTodo(
            1,
            1,
            "SubTask1",
            "Maintain Property",
            LocalDateTime.now(),
            LocalDateTime.now(),
            true,
            2
        )
    )
    subTaskList.add(
        SubTodo(
            2,
            1,
            "SubTask2",
            "Inspect Property",
            LocalDateTime.now(),
            LocalDateTime.now(),
            true,
            1
        )
    )
    subTaskList.add(
        SubTodo(
            1,
            1,
            "SubTask1",
            "Maintain Property",
            LocalDateTime.now(),
            LocalDateTime.now(),
            true,
            2
        )
    )
    subTaskList.add(
        SubTodo(
            2,
            1,
            "SubTask2",
            "Inspect Property",
            LocalDateTime.now(),
            LocalDateTime.now(),
            true,
            1
        )
    )
    subTaskList.add(
        SubTodo(
            1,
            1,
            "SubTask1",
            "Maintain Property",
            LocalDateTime.now(),
            LocalDateTime.now(),
            true,
            2
        )
    )
    subTaskList.add(
        SubTodo(
            2,
            1,
            "SubTask2",
            "Inspect Property",
            LocalDateTime.now(),
            LocalDateTime.now(),
            true,
            1
        )
    )
    subTaskList.add(
        SubTodo(
            1,
            1,
            "SubTask1",
            "Maintain Property",
            LocalDateTime.now(),
            LocalDateTime.now(),
            true,
            2
        )
    )
    subTaskList.add(
        SubTodo(
            2,
            1,
            "SubTask2",
            "Inspect Property",
            LocalDateTime.now(),
            LocalDateTime.now(),
            true,
            1
        )
    )
    // val todos by viewModel.todoListBasedOnId.collectAsState(initial = emptyList())
    var mainTask = Todo(
        todoId = 1,
        title = "Main Task One",
        status = false,
        priority = 2,
        longitude = 54.0,
        latitude = 55.0,
        label = "Maintenance",
        dueDate = LocalDateTime.now(),
        createdDate = LocalDateTime.now(),
        description = "Property in Lahore"
    )
    var mainTaskOne = TodoWithSubTodos1(
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
            description = "Property in Lahore"
        ), subtodos = subTaskList, images = emptyList()
    )

    val list: MutableList<TodoWithSubTodos1> = mutableListOf()
    list.add(mainTaskOne)
    list.add(mainTaskOne)
    return list;

}