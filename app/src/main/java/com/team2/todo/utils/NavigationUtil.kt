package com.team2.todo.utils

/**
 * Created by Manu KJ on 11/1/23.
 */

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.team2.todo.screens.MainScreen
import com.team2.todo.screens.add_todo.AddTodos
import com.team2.todo.screens.details_page.DetailsPage
import com.team2.todo.screens.subtodo_details.SubTodoDetails

// Enum of all the Screen
enum class Screen {
    MainScreen, CompletedListing,AddTodos,DetailsScreen, SubTodoDetails
}

object NavigationUtil {
    lateinit var navController: NavHostController

    // initializing the nav controller before using it
    fun init(navController: NavHostController) {
        this.navController = navController;
    }

    // navigate to the given Enum
    fun navigateTo(screen: Screen) {
        navController.navigate(screen.name)
    }

    fun goBack(){
        navController.popBackStack();
    }
}


@Composable
fun NavHostControllerProvider() {
    NavHost(navController = NavigationUtil.navController, startDestination = Screen.MainScreen.name) {
        composable(Screen.MainScreen.name) { MainScreen() }
//        composable(Screen.CompletedListing.name) { CompletedListing() }
        composable(Screen.AddTodos.name) { AddTodos(false) }
        composable(Screen.DetailsScreen.name) { DetailsPage() }
        composable(
            route = "${Screen.SubTodoDetails.name}/{subTodoId}",
            arguments = listOf(navArgument("subTodoId") { type = NavType.LongType })
        ) { backStackEntry ->
            val subTodoId = backStackEntry.arguments?.getLong("subTodoId") ?: -1
            SubTodoDetails(subTodoId)

        }

    }
}