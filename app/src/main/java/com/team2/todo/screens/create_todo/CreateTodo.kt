package com.team2.todo.screens.create_todo
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.team2.todo.data.RealEstateDatabase
import com.team2.todo.data.repo.TodoRepo
import com.team2.todo.screens.create_todo.ui_components.createtodo_ui.CreateTodoUI
import com.team2.todo.screens.create_todo.view_model.CreateTodoViewModel


@Composable
fun CreateTodo() {
    val todoContext = LocalContext.current
    val database = RealEstateDatabase.getInstance(todoContext)
    val todoRepo = TodoRepo(database)
    val viewModel = CreateTodoViewModel(todoRepo)
    CreateTodoUI(viewModel)
}
