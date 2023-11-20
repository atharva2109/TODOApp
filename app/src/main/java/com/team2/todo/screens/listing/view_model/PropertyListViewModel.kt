package com.team2.todo.screens.listing.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.team2.todo.data.entities.Todo
import com.team2.todo.data.entities.relations.TodoWithSubTodos
import com.team2.todo.data.repo.TodoRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * Created by Manu KJ on 11/14/23.
 */

class PropertyListViewModel(private var repo  : TodoRepo) : ViewModel() {
    private val uncompletedPropertyList : MutableStateFlow<List<TodoWithSubTodos?>> = MutableStateFlow(
        emptyList()
    )
    var uncompletedProperties : StateFlow<List<TodoWithSubTodos?>> = uncompletedPropertyList

    val completedPropertyList = MutableLiveData<List<TodoWithSubTodos>>()


    fun updatedUncompletedPropertyList() {
        viewModelScope.launch {
            try {
                repo.getAllTodosWithSubTodos().collect{

                    uncompletedPropertyList.value = it
                }

            } catch (e: Exception) {

            }
        }
    }

    fun updatedCompletedPropertyList(newList: List<TodoWithSubTodos>) {
        viewModelScope.launch {
            try {
                completedPropertyList.value = newList
            } catch (e: Exception) {

            }
        }
    }

    fun updateTaskStatus(taskId: Int, completed: Boolean) {

    }
}