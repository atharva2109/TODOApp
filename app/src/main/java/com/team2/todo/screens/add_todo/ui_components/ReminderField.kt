package com.team2.todo.screens.add_todo.ui_components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

/**
 * Created by Atharva K on 11/16/23.
 */
@Composable
fun ReminderField(dateSelected: LocalDateTime) {
    val reminderDate =dateSelected.minusDays(1)

    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    val formattedDate = reminderDate.format(formatter)

    Text(
        text = "We will remind you on: $formattedDate",
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        fontSize = 15.sp
    )
}