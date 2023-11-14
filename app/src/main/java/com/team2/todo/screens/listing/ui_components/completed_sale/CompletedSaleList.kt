package com.team2.todo.screens.listing.ui_components.completed_sale

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.team2.todo.R
import com.team2.todo.common_ui_components.EmptyList
import com.team2.todo.screens.listing.view_model.PropertyListViewModel

/**
 * Created by Manu KJ on 11/6/23.
 */
@Composable
fun CompletedSaleList(viewModel: PropertyListViewModel = viewModel()) {
    val list = viewModel.completedPropertyList.value
    EmptyList(title = "Sale List Empty", drawableID = R.drawable.ic_no_completed_list)
}