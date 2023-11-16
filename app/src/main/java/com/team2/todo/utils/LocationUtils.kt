package com.team2.todo.utils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import androidx.core.app.ActivityCompat

/*
* Created by Vivek Tate on 10/11/2023
* */
object LocationUtils {

    private lateinit var context: Context

    private var currentLocation: Location? = null
    private lateinit var locationManager: LocationManager

    fun init(context: Context) {
        this.context = context
    }

    fun getCurrentLocation(callback: (Location) -> Unit) {
        fetchCurrentLocation() { location: Location ->
            callback(location)
        }
    }

    private fun fetchCurrentLocation(callback: (Location) -> Unit) {
        locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager

        val hasGps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        val hasNetwork = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
        val gpsLocationListener: LocationListener = object : LocationListener {
            override fun onLocationChanged(location: Location) {
                callback(location!!)
                locationManager.removeUpdates(this)
            }

            override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
            override fun onProviderEnabled(provider: String) {}
            override fun onProviderDisabled(provider: String) {}
        }

        val networkLocationListener: LocationListener = object : LocationListener {
            override fun onLocationChanged(location: Location) {
                callback(location!!)
                locationManager.removeUpdates(this)
            }

            override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
            override fun onProviderEnabled(provider: String) {}
            override fun onProviderDisabled(provider: String) {}
        }
        if (hasGps || hasNetwork) {
            if (hasGps) {
                if (ActivityCompat.checkSelfPermission(
                        context,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                        context,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    // If permission is not granted
                }
                locationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    5000,
                    0F,
                    gpsLocationListener
                )
            }
            if (hasNetwork) {
                locationManager.requestLocationUpdates(
                    LocationManager.NETWORK_PROVIDER,
                    5000,
                    0F,
                    networkLocationListener
                )
            }

        }
    }
}