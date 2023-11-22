package com.team2.todo.utils

import android.Manifest
import android.app.Activity
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
    private lateinit var activity: Activity

    private var currentLocation: Location? = null
    private lateinit var locationManager: LocationManager

    fun init(context: Context, activity: Activity) {
        this.context = context
        this.activity = activity
    }

    fun getCurrentLocation(callback: (Location) -> Unit) {
        fetchCurrentLocation {
            callback(it)
        }
    }


    private fun fetchCurrentLocation(callback: (Location) -> Unit) {

        locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager

        val hasGps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        val hasNetwork = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)

        if (hasGps || hasNetwork) {
            if (hasGps && !hasNetwork) {
                fetchLocationViaGPS {
                    callback(it)
                }
            } else if (!hasGps && hasNetwork) {
                fetchLocationViaNetwork {
                    callback(it)
                }
            } else if (hasGps && hasNetwork) {
                fetchLocationViaGPS { gpsLocation: Location ->
                    fetchLocationViaNetwork {networkLocation: Location ->
                        if (gpsLocation!= null && networkLocation != null) {
                            if (gpsLocation.accuracy > networkLocation.accuracy) {
                                callback(gpsLocation)
                            } else {
                                callback(networkLocation)
                            }
                        }
                    }
                }
            }
        }
    }

    private fun fetchLocationViaGPS(callback: (Location) -> Unit) {

        val gpsLocationListener: LocationListener = object : LocationListener {
            override fun onLocationChanged(location: Location) {
                callback(location!!)
                locationManager.removeUpdates(this)
            }
            override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
            override fun onProviderEnabled(provider: String) {}
            override fun onProviderDisabled(provider: String) {}
        }

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
        }
        locationManager.requestLocationUpdates(
            LocationManager.GPS_PROVIDER,
            5000,
            0F,
            gpsLocationListener
        )
    }

    private fun fetchLocationViaNetwork(callback: (Location) -> Unit) {

        val networkLocationListener: LocationListener = object : LocationListener {
            override fun onLocationChanged(location: Location) {
                callback(location!!)
                locationManager.removeUpdates(this)
            }
            override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
            override fun onProviderEnabled(provider: String) {}
            override fun onProviderDisabled(provider: String) {}
        }

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
        }
        locationManager.requestLocationUpdates(
            LocationManager.NETWORK_PROVIDER,
            5000,
            0F,
            networkLocationListener
        )
    }
}