package com.team2.todo.utils

/**
 * Created by Manu KJ on 11/1/23.
 */

import SubTaskDetailsPage
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.team2.todo.data.entities.SubTodo
import com.team2.todo.screens.details_page.DetailsPage
import com.team2.todo.screens.MainScreen
import com.team2.todo.screens.create_todo.CreateTodo
import com.team2.todo.screens.listing.Listing

// Enum of all the Screen
enum class Screen {
    MainScreen, DetailsScreen, Listing, CreateTodo, SubTodoDetails
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

    fun navigateTo(path: String) {
        navController.navigate(path)
    }
}


@Composable
fun NavHostControllerProvider() {
    NavHost(
        navController = NavigationUtil.navController,
        startDestination = Screen.DetailsScreen.name
    ) {
        composable(Screen.MainScreen.name) { MainScreen() }
        composable(Screen.DetailsScreen.name) { DetailsPage() }
        composable(Screen.Listing.name) { Listing() }
        composable(Screen.CreateTodo.name) { CreateTodo() }
        composable(
            route = "${Screen.SubTodoDetails.name}/{subTodoId}",
            arguments = listOf(navArgument("subTodoId") { type = NavType.IntType })
        ) { backStackEntry ->
            val subTodoId = backStackEntry.arguments?.getInt("subTodoId") ?: -1
            SubTaskDetailsPage(subTodoId)
        }
    }
}