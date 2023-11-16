package com.team2.todo.screens.add_todo.ui_components

import android.util.Log
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun ReminderField(dateselected: String, timeselected: String) {
    val dateTime = "$dateselected $timeselected"
    Log.d("Date-Time", dateTime)
    var formattedText by remember { mutableStateOf("") }


            try {
                val format = SimpleDateFormat("dd/MM/yyyy hh:mm", Locale.getDefault())
                val dateObj = format.parse(dateTime)

                if (dateObj != null) {
                    val reminderTime = dateObj.time - 1 * 24 * 60 * 60 * 1000
                    val reminderDate = Date(reminderTime)
                    val reminderFormat = SimpleDateFormat("dd/MM/yyyy hh:mm", Locale.getDefault())
                    val date = reminderFormat.format(reminderDate)
                    val time = reminderFormat.format(reminderTime)
                    formattedText = "$date"

                } else {
                    formattedText = "Invalid date"
                }

            } catch (e: ParseException) {
                formattedText = "Error parsing date"
            }

    Text(text = "We will remind you on: $formattedText", fontWeight = FontWeight.Bold, textAlign = TextAlign.Center )
}