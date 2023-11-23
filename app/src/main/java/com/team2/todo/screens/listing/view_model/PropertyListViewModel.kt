package com.team2.todo.screens.listing.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.team2.todo.common_ui_components.filter.view_model.Filter
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

class PropertyListViewModel(val repo: TodoRepo) : ViewModel() {
    var inSalePropertyList = MutableStateFlow<List<TodoWithSubTodos>>(emptyList())
    var completedPropertyList = MutableStateFlow<List<TodoWithSubTodos>>(emptyList())

    init {
        fetchUpdatedList()
    }


    fun fetchUpdatedList() {
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

//    fun updateInSalePropertyList(newList: List<TodoWithSubTodos>) {
//        inSalePropertyList.value = newList
//    }
//
//    fun updateCompletedPropertyList(newList: List<TodoWithSubTodos>) {
//        completedPropertyList.value = newList
//    }

    fun dataForSelectedFilter(selectedFilter: Filter, status: Boolean) {
        fetchDataForSelectedFilter( selectedFilter, status) {
            System.out.println("UPDATED LIST - " + it)
            if (!status) {
                    inSalePropertyList.value = it

            } else {
                    completedPropertyList.value = it

            }
        }
    }
    private fun fetchDataForSelectedFilter(selectedFilter: Filter, status: Boolean, callback: (List<TodoWithSubTodos>) -> Unit) {

        when(selectedFilter) {

            Filter.DEFAULT_FILTER -> {
                viewModelScope.launch {
                    repo.getAllTodosWithSubTodos(status = status).collect { list ->
                        callback(list)
                    }
                }
            }

            Filter.DUE_DATE -> {
                viewModelScope.launch {
                    repo.getAllTodosOrderedByDueDateDESCWithSubTodos(status = status).collect { list ->
                        callback(list)
                    }
                }
            }

            Filter.HIGH_PRIORITY -> {
                viewModelScope.launch {
                    repo.getAllTodosOrderedByPriorityDESCWithSubTodos(status = status).collect { list ->
                        callback(list)
                    }
                }
            }

            Filter.LOW_PRIORITY -> {
                viewModelScope.launch {
                    repo.getAllTodosOrderedByPriorityASCWithSubTodos(status = status).collect { list ->
                        callback(list)
                    }
                }
            }

            Filter.GEO_LOCATION -> {
                viewModelScope.launch {
                    repo.getAllTodosOrderedByDueDateDESCWithSubTodos(status = status).collect { list ->
                        callback(list)
                    }
                }
            }

            Filter.HIGH_PRICE -> {
                viewModelScope.launch {
                    repo.getAllTodosOrderedByPriceDESCWithSubTodos(status = status).collect { list ->
                        callback(list)
                    }
                }
            }

            Filter.LOW_PRICE -> {
                viewModelScope.launch {
                    repo.getAllTodosOrderedByPriceASCWithSubTodos(status = status).collect { list ->
                        callback(list)
                    }
                }
            }
        }

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

    fun deleteTheProperty(todoId: Long) {
        viewModelScope.launch {
            repo.deleteProperty(todoId)
        }
    }
}
