package com.team2.todo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.registerForActivityResult
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.team2.todo.ui.theme.TODOTheme
import com.team2.todo.utils.NavHostControllerProvider
import com.team2.todo.utils.NavigationUtil
import com.team2.todo.utils.NotificationUtil

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TODOTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // initalize utils
                    val navController = rememberNavController()
                    NavigationUtil.init(navController)
                    NotificationUtil.init(this)

                    //Navigation Provider i,e the Navigation graph
                    NavHostControllerProvider()

                    //navigate to inital page

                }
            }
        }
    }
}
