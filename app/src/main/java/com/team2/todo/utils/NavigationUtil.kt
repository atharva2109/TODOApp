package com.team2.todo.utils

/**
 * Created by Manu KJ on 11/1/23.
 */

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.team2.todo.screens.details_page.DetailsPage
import com.team2.todo.screens.MainScreen

// Enum of all the Screen
enum class Screen {
    MainScreen, DetailsScreen,
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

    fun navigateToCompletedListScreen() {
        TODO("Not yet implemented")
    }
}


@Composable
fun NavHostControllerProvider() {
    NavHost(navController = NavigationUtil.navController, startDestination = Screen.MainScreen.name) {
        composable(Screen.MainScreen.name) { MainScreen() }
        composable(Screen.DetailsScreen.name) { DetailsPage() }
    }
}