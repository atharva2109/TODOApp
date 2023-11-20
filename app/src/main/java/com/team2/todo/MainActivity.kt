package com.team2.todo


import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.team2.todo.data.RealEstateDatabase
import com.team2.todo.data.repo.TodoRepo
import com.team2.todo.ui.theme.TODOTheme
import com.team2.todo.utils.NavHostControllerProvider
import com.team2.todo.utils.NavigationUtil
import com.team2.todo.utils.NotificationUtil
import com.team2.todo.screens.listing.view_model.PropertyListViewModel
import com.team2.todo.utils.LocationUtils
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.launch
import kotlin.math.log

class MainActivity : ComponentActivity() {

    private var currentLocation: Location? = null
    private lateinit var locationManager: LocationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fetchAndUpdateList()
        setContent {
            TODOTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Initialise Utils
                    val navController = rememberNavController()
                    NavigationUtil.init(navController)
                    NotificationUtil.init(this)
                    LocationUtils.init(this, this)

                    //Navigation Provider i,e the Navigation graph
                    NavHostControllerProvider()
                }
            }
        }
    }

    private fun fetchAndUpdateList() {
        val viewModel: PropertyListViewModel by viewModels()
        val database = RealEstateDatabase.getInstance(context = this);
        val response = TodoRepo(database).getAllTodosWithSubTodos();

        lifecycleScope.launch {
            response.collect { list ->
                run {
                    viewModel.updatedUncompletedPropertyList(list)
                    println("viewmodal data" + list.size);
                }
            }
        }
    }
}
