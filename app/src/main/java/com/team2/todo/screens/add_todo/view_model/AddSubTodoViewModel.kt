package com.team2.todo.screens.add_todo.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.team2.todo.data.entities.SubTodo
import com.team2.todo.data.repo.SubTodoRepo
import com.team2.todo.data.repo.TodoRepo
import kotlinx.coroutines.launch

class AddSubTodoViewModel(val repository: SubTodoRepo): ViewModel() {
    fun addSubTodo(subtodo:SubTodo){
        viewModelScope.launch {
            repository.upsertSubTodo(subtodo)
        }
    }
}