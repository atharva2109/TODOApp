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


object ListingViewModel {
    private lateinit var repo: TodoRepo
    lateinit var instance: PropertyListViewModel

    fun initialize(repo: TodoRepo) {
        this.repo = repo
        instance = PropertyListViewModel(repo)
    }

}

class PropertyListViewModel(private val repo: TodoRepo) : ViewModel() {
    var inSalePropertyList = MutableStateFlow<List<TodoWithSubTodos>>(emptyList())
    var completedPropertyList = MutableStateFlow<List<TodoWithSubTodos>>(emptyList())

    init {
        fetchUpdatedList()
    }


    fun fetchUpdatedList(){
        fetchCompletedList()
        fetchInSaleList()
    }

    fun updateStatus(todoId: Long, status: Boolean): Boolean {
        viewModelScope.launch {
            repo.updateTodoStatus(todoId, status)
            fetchUpdatedList()
        }
        return true;
    }

    private fun fetchCompletedList() {
        viewModelScope.launch {
            repo.getAllTodosWithSubTodos(status = true).collect { list ->
                run {
                    completedPropertyList.emit(list)
                }
            }
        }
    }

    private fun fetchInSaleList() {
        viewModelScope.launch {
            repo.getAllTodosWithSubTodos(status = false).collect { list ->
                run {
                    inSalePropertyList.emit(list)
                }
            }
        }
    }
}
