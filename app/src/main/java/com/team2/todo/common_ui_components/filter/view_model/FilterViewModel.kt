package com.team2.todo.common_ui_components.filter.view_model

import androidx.compose.runtime.ComposeNodeLifecycleCallback
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.team2.todo.data.entities.relations.TodoWithSubTodos
import com.team2.todo.screens.listing.view_model.PropertyListViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

/*
* Created by Vivek Tate on 18/11/2023
*/

class FilterViewModel(): ViewModel() {

    private val _selectedFilter = mutableStateOf(getAllFilters()[0])

    val selectedFilter: State<Filter>
        get() = _selectedFilter

    fun setSelectedFilter(filter: Filter) {
        _selectedFilter.value = filter
    }

    fun getAllFilters(): List<Filter>{
        return listOf(
            Filter.DEFAULT_FILTER,
            Filter.DUE_DATE,
            Filter.HIGH_PRIORITY,
            Filter.LOW_PRIORITY,
            Filter.GEO_LOCATION,
            Filter.HIGH_PRICE,
            Filter.LOW_PRICE
        )
    }

    fun getFilter(value: String): Filter? {
        val map = Filter.values().associateBy(Filter::value)
        return map[value]
    }

}

enum class Filter(val value: String) {

    DEFAULT_FILTER("Date of Creation"),
    GEO_LOCATION("Nearby Location"),
    DUE_DATE("Deadline"),
    HIGH_PRIORITY("Priority: High to Low"),
    LOW_PRIORITY("Priority: Low to High"),
    HIGH_PRICE("Price: High to Low"),
    LOW_PRICE("Price: Low to High")
}