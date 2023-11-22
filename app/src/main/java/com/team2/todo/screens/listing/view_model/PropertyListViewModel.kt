package com.team2.todo.screens.listing.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.team2.todo.data.entities.relations.TodoWithSubTodos
import com.team2.todo.data.repo.TodoRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

/**
 * Created by Manu KJ on 11/14/23.
 */

class PropertyListViewModel(private val repo: TodoRepo) : ViewModel() {
    var inSalePropertyList = MutableStateFlow<List<TodoWithSubTodos>>(emptyList())
    var completedPropertyList = MutableStateFlow<List<TodoWithSubTodos>>(emptyList())

    init {
        fetchCompletedList()
        fetchInSaleList()
    }


    fun updateStatus(todoId: Long, status: Boolean): Boolean {
        viewModelScope.launch {
            repo.updateTodoStatus(todoId, status)
            fetchCompletedList()
            fetchInSaleList()
        }
        return true;
    }

    fun fetchCompletedList() {
        viewModelScope.launch {
            repo.getAllTodosWithSubTodos(status = true).collect { list ->
                run {
                    completedPropertyList.emit(list)
                }
            }
        }
    }

    fun fetchInSaleList() {
        viewModelScope.launch {
            repo.getAllTodosWithSubTodos(status = false).collect { list ->
                run {
                    inSalePropertyList.emit(list)
                }
            }
        }
    }
}
