package com.team2.todo.common_ui_components.filter

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

/*
* Created by Vivek Tate on 18/11/2023
*/

class FilterViewModel: ViewModel() {
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
            Filter.GEO_LOCATION
        )
    }

    fun getFilter(value: String): Filter? {
        val map = Filter.values().associateBy(Filter::value)
        return map[value]
    }
}

enum class Filter(val value: String){
    DEFAULT_FILTER("Date of Creation"),
    GEO_LOCATION("Nearby Location"),
    DUE_DATE("Deadline"),
    HIGH_PRIORITY("Priority: High to Low"),
    LOW_PRIORITY("Priority: Low to High"),
}