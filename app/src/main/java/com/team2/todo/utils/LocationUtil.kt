package com.team2.todo.utils

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.app.ActivityCompat

/*
* Created by Vivek Tate on 10/11/2023
* */
object LocationUtil {

    private lateinit var context: Context
    private lateinit var activity: Activity

    var currentLocation: Location? by mutableStateOf<Location?>(null)
        private set

    private lateinit var locationManager: LocationManager

    fun init(context: Context, activity: Activity, locationManager: LocationManager) {
        this.context = context
        this.activity = activity
        this.locationManager = locationManager

        fetchCurrentLocation()
    }

    private fun _setLocation(newLocation: Location?) {
        currentLocation = newLocation
    }

    fun updateLocation(newLocation: Location) {
        _setLocation(newLocation)
    }

    fun invalidate() {
        _setLocation(null)
    }

    fun valid(): Boolean {
        return (currentLocation != null)
    }

    private fun fetchCurrentLocation() {
        fetchLocationViaGPS()
    }

    private fun fetchLocationViaGPS() {

        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                activity,
                arrayOf(
                    android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION
                ),
                2
            )
        } else {
            println("ca")
            val lastKnownLocation =
                locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            if (lastKnownLocation != null) {
                updateLocation(lastKnownLocation)
            }
            locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                5000,
                0F,
                GPSGeoLocationListener
            )
        }
    }

}

object GPSGeoLocationListener : LocationListener {
    override fun onLocationChanged(location: Location) {
        LocationUtil.updateLocation(location)
    }

}