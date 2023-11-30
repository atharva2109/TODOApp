package com.team2.todo


import android.content.Context
import android.location.LocationManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.team2.todo.ui.theme.TODOTheme
import com.team2.todo.utils.GPSGeoLocationListener
import com.team2.todo.utils.NavHostControllerProvider
import com.team2.todo.utils.NavigationUtil
import com.team2.todo.utils.NotificationUtil
import com.team2.todo.utils.LocationUtil
import com.team2.todo.utils.PermissionUtil

class MainActivity : ComponentActivity() {

    private lateinit var locationManager: LocationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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

                    locationManager = applicationContext.getSystemService(Context.LOCATION_SERVICE) as LocationManager
                    LocationUtil.init(this, this, locationManager)

                    //Navigation Provider i,e the Navigation graph
                    NavHostControllerProvider()
                }
            }
        }
    }

    override fun onPause() {
        super.onPause()

        locationManager.removeUpdates(GPSGeoLocationListener)
    }

    override fun onResume() {
        super.onResume()

        locationManager = applicationContext.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        LocationUtil.init(this, this, locationManager)
    }
}



