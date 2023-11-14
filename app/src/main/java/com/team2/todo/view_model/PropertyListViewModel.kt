package com.team2.todo.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.team2.todo.data.entities.Todo
import kotlinx.coroutines.launch

/**
 * Created by Manu KJ on 11/14/23.
 */

class PropertyListViewModel : ViewModel() {
    private val uncompletedPropertyList = MutableLiveData<List<Todo>>()
    private val completedPropertyList = MutableLiveData<List<Todo>>()

    fun updatedUncompletedPropertyList(newList: List<Todo>) {
        viewModelScope.launch {
            try {
                uncompletedPropertyList.value = newList
            } catch (e: Exception) {

            }
        }
    }

    fun updatedCompletedPropertyList(newList: List<Todo>) {
        viewModelScope.launch {
            try {
                completedPropertyList.value = newList
            } catch (e: Exception) {

            }
        }
    }
}