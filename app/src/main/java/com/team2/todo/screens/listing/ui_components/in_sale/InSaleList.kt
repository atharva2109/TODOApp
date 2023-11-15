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
import com.team2.todo.data.RealEstateDatabase
import com.team2.todo.data.datautils.LocalDatetimeToWords
import com.team2.todo.data.repo.SubTodoRepo
import com.team2.todo.data.repo.TodoRepo
import com.team2.todo.screens.details_page.ui_components.TodosCard
import com.team2.todo.screens.details_page.ui_components.TodosCardMain
import com.team2.todo.screens.details_page.view_model.DetailsPageViewModel
import com.team2.todo.utils.NavigationUtil
import com.team2.todo.utils.Screen

/**
 * Created by Manu KJ on 11/6/23.
 */

@Composable
fun InSaleList() {




    val todoContext = LocalContext.current
    val database = RealEstateDatabase.getInstance(todoContext)
    val todoRepo = TodoRepo(database)
    val subTodoRepo = SubTodoRepo(database)

    val viewModel = DetailsPageViewModel(todoRepo, subTodoRepo, )

    val todos by viewModel.todosList.collectAsState(initial = emptyList())
    if (todos.isNotEmpty()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.Center

        ) {
            items(todos) { item ->



                var dateTime =
                    LocalDatetimeToWords.formatLocalDateTimeAsWords(item.todo.createdDate)
                Spacer(modifier = Modifier.height(16.dp))
                Box(Modifier.clickable {

                    NavigationUtil.navigateTo("${Screen.DetailsScreen.name}/${item.todo.todoId}")
                }) {

                    TodosCardMain(todos = item, dateTime = dateTime)
                }

            }

        }
    } else {
        EmptyList(title = "No Active Sales Found", drawableID = R.drawable.ic_no_in_sale_list)

    }
    
}




