package com.team2.todo.screens.listing.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.team2.todo.data.entities.Todo
import com.team2.todo.data.entities.relations.TodoWithSubTodos
import kotlinx.coroutines.launch

/**
 * Created by Manu KJ on 11/14/23.
 */

class PropertyListViewModel : ViewModel() {
    val uncompletedPropertyList = MutableLiveData<List<TodoWithSubTodos>>()
    val completedPropertyList = MutableLiveData<List<TodoWithSubTodos>>()

    fun updatedUncompletedPropertyList(newList: List<TodoWithSubTodos>) {
        viewModelScope.launch {
            try {
                uncompletedPropertyList.value = newList
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
}