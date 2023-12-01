package com.team2.todo.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.ui.graphics.Color
import androidx.core.content.ContextCompat
import com.team2.todo.screens.add_todo.ui_components.DatePickerComponent
import com.team2.todo.screens.add_todo.ui_components.TimePickerComponent
import com.team2.todo.ui.theme.PriorityHigh
import com.team2.todo.ui.theme.PriorityLow
import com.team2.todo.ui.theme.PriorityMedium
import java.lang.reflect.Modifier
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

object AppUtil {
    fun getPriorityString(priorityIndex: Int): String {
        var priority = "Low";
        if (priorityIndex == 1) {
            priority = "Medium"
        }
        if (priorityIndex == 2) {
            priority = "High"
        }
        return priority;
    }

    fun getPriorityColor(priorityIndex: Int): Color {
        var color = PriorityLow
        if (priorityIndex == 1) {
            color = PriorityMedium
        }
        if (priorityIndex == 2) {
            color = PriorityHigh
        }
        return color
    }

    fun openMaps(lat: Double, lon: Double,context: Context){
        val stringLat = lat.toString()
        val stringLon = lon.toString()
        val intentUri = Uri.parse("geo:$stringLat,$stringLon")
        val intent = Intent(Intent.ACTION_VIEW, intentUri)
        intent.setPackage("com.google.android.apps.maps")
        ContextCompat.startActivity(context, intent, null)
    }
}

fun checkOverdue(dateselected: LocalDateTime): Boolean {
        val currentDate = LocalDateTime.now()
       return dateselected.isBefore(currentDate)

}
