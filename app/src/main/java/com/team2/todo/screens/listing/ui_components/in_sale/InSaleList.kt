package com.team2.todo.screens.listing.ui_components.in_sale

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
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
