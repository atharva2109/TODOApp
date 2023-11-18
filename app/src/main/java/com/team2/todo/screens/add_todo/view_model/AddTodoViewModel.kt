package com.team2.todo.screens.add_todo.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.team2.todo.data.entities.Todo
import com.team2.todo.data.repo.TodoRepo
import kotlinx.coroutines.launch

class AddTodoViewModel(val repository:TodoRepo):ViewModel() {
    fun addTodo(todo:Todo){
        viewModelScope.launch {
            repository.upsertTodo(todo)
        }
    }
}