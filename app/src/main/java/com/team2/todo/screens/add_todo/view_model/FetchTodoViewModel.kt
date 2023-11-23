package com.team2.todo.screens.add_todo.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.team2.todo.data.entities.Images
import com.team2.todo.data.entities.relations.TodoWithSubTodos
import com.team2.todo.data.repo.TodoRepo
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class FetchTodoViewModel(val repository: TodoRepo): ViewModel() {
    suspend fun fetchTodo(todoid:Long):Flow<List<TodoWithSubTodos>>{
        delay(2000)
        return repository.getTodoWithSubTodosBasedOnTodoId(todoid)
    }

    fun getTodoImages(id: Long): Flow<List<Images>> {
        return repository.getAllTodoImagesBasedOnTodo(id)
    }
}