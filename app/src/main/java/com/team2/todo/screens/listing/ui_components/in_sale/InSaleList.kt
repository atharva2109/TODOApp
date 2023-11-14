package com.team2.todo.screens.listing.ui_components.in_sale

import androidx.compose.runtime.Composable
import com.team2.todo.R
import com.team2.todo.common_ui_components.EmptyList
import com.team2.todo.screens.listing.view_model.PropertyListViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

/**
 * Created by Manu KJ on 11/6/23.
 */

@Composable
fun InSaleList(viewModel: PropertyListViewModel = viewModel()) {
    val list = viewModel.uncompletedPropertyList.value
    EmptyList(title = "No Active Sales Found", drawableID = R.drawable.ic_no_in_sale_list)
}