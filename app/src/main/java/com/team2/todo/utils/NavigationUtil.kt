package com.team2.todo.utils

import androidx.navigation.NavHostController

enum class Screen {
    Listing, AddOREditTODO, CompletedListing,
}

object NavigationUtil {
    lateinit var navController: NavHostController

    fun init(navController: NavHostController) {
        this.navController = navController;
    }

    fun navigateTo(screen: Screen) {
        navController.navigate(screen.name)
    }
}
