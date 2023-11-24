package com.team2.todo.screens.listing.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.team2.todo.common_ui_components.filter.view_model.Filter
import com.team2.todo.common_ui_components.filter.view_model.FilterViewModel
import com.team2.todo.data.entities.relations.TodoWithSubTodos
import com.team2.todo.data.repo.TodoRepo
import com.team2.todo.utils.GeoFenceUtil
import com.team2.todo.utils.LocationUtil
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

/**
 * Created by Manu KJ on 11/14/23.
 */


object ListingViewModel {
    private lateinit var repo: TodoRepo
    lateinit var instance: PropertyListViewModel

    fun initialize(repo: TodoRepo, filterViewModel: FilterViewModel) {
        this.repo = repo
        instance = PropertyListViewModel(repo, filterViewModel)
    }

}

class PropertyListViewModel(val repo: TodoRepo,var filterViewModel: FilterViewModel) : ViewModel() {
    var inSalePropertyList = MutableStateFlow<List<TodoWithSubTodos>>(emptyList())
    var completedPropertyList = MutableStateFlow<List<TodoWithSubTodos>>(emptyList())

    init {
        fetchUpdatedList()
    }


    fun fetchUpdatedList() {
        getDataForSelectedFilter(filterViewModel.selectedFilter.value)
    }

    fun updateStatus(todoId: Long, status: Boolean): Boolean {
        viewModelScope.launch {
            repo.updateTodoStatus(todoId, status)
            fetchUpdatedList()
        }
        return true;
    }

    fun getDataForSelectedFilter(selectedFilter: Filter) {

        fetchDataForSelectedFilter( selectedFilter, false) {
            inSalePropertyList.value = it
        }

        fetchDataForSelectedFilter( selectedFilter, true) {
            completedPropertyList.value = it
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
                    repo.getAllTodosWithSubTodos(status = status).collect { list ->
                        LocationUtil.getCurrentLocation { location ->
                            callback(GeoFenceUtil.sortLocationByDistance(list, location))
                        }
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

    fun deleteTheProperty(todoId: Long) {
        viewModelScope.launch {
            repo.deleteProperty(todoId)
        }
    }
}
