package com.team2.todo.common_ui_components.filter.ui_components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.team2.todo.common_ui_components.filter.view_model.FilterViewModel
import com.team2.todo.screens.listing.view_model.PropertyListViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterChipGroup(viewModel: PropertyListViewModel, isCompletedTaskScreen: Boolean) {

    var filterViewModel = FilterViewModel(viewModel, !isCompletedTaskScreen)

    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        items(filterViewModel.getAllFilters()) { filter ->
            FilterChip(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp)
                    .height(50.dp),
                selected = (filter == filterViewModel.selectedFilter.value),
                onClick = {
                    filterViewModel.setSelectedFilter(filter)

                          },
                label = {
                    Text(text = filter.value)
                })
        }
    }
}