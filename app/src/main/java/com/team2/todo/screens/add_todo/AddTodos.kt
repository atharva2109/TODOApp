package com.team2.todo.screens.add_todo

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.*
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold

import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.team2.todo.common_ui_components.location.VerifyByLocationCompose
import com.team2.todo.data.RealEstateDatabase
import com.team2.todo.data.daos.TodoDao
import com.team2.todo.data.entities.Todo
import com.team2.todo.data.repo.TodoRepo
import com.team2.todo.screens.add_todo.ui_components.AddEditAppBar
import com.team2.todo.screens.add_todo.ui_components.DateAndTimeField
import com.team2.todo.screens.add_todo.ui_components.DatePickerComponent
import com.team2.todo.screens.add_todo.ui_components.DropDownMenuComponent
import com.team2.todo.screens.add_todo.ui_components.PickImageFromGallery
import com.team2.todo.screens.add_todo.ui_components.ReminderField
import com.team2.todo.screens.add_todo.ui_components.TimePickerComponent
import com.team2.todo.screens.add_todo.view_model.AddTodoViewModel
import com.team2.todo.ui.theme.PrimaryColor
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatterBuilder
import java.time.format.DateTimeParseException
import java.time.temporal.ChronoField

/**
 * Created by Atharva K on 11/13/23.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTodos() {
    val OutLineTextColor = OutlinedTextFieldDefaults.colors(
        focusedBorderColor = Color.Black,
        unfocusedBorderColor = PrimaryColor,
        focusedLabelColor = Color.Black,
        disabledLabelColor = PrimaryColor
    )
    val OutLinedTextModifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 5.dp)

    var ctx= LocalContext.current
    var db=RealEstateDatabase.getInstance(ctx)
    var repository=TodoRepo(db)
    var viewModel=AddTodoViewModel(repository)

    var enteredTitle by remember {
        mutableStateOf("")
    }
    var enteredLabel by remember {
        mutableStateOf("")
    }
    var enteredDescription by remember {
        mutableStateOf("")
    }

    var enteredPrice by remember {
        mutableStateOf(0.0)
    }

    var isTitleEmpty by remember { mutableStateOf(false) }
    var isLabelEmpty by remember { mutableStateOf(false) }
    var isDescriptionEmpty by remember { mutableStateOf(false) }

    var (calendarState, dateselected) = DatePickerComponent()
    var (timeState, timeselected) = TimePickerComponent()

    var currentlatitude by remember {
        mutableStateOf(0.0)
    }
    var currentlongitude by remember {
        mutableStateOf(0.0)
    }
    // Define the format of your date and time strings
    val dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    val timeFormat = DateTimeFormatter.ofPattern("HH:m")
    var localdateTime:LocalDateTime= LocalDateTime.now()

    var selectpriorityindex by remember {
        mutableStateOf(0)
    }
    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(it)
        ) {
            AddEditAppBar()
            Column(
                modifier = Modifier
                    .weight(weight = 1.0F)
                    .fillMaxWidth()
                    .padding(horizontal = 25.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                //Title
                OutlinedTextField(
                    modifier = OutLinedTextModifier,
                    value = enteredTitle,
                    onValueChange = {
                        enteredTitle = it
                        isTitleEmpty = it.isEmpty()
                    },
                    label = { Text(text = "Title") },
                    colors = OutLineTextColor,
                    isError = isTitleEmpty,
                )
                //Label
                OutlinedTextField(
                    modifier = OutLinedTextModifier,
                    value = enteredLabel,
                    onValueChange = {
                        enteredLabel = it
                        isLabelEmpty = it.isEmpty()
                    },
                    label = { Text(text = "Label") },
                    colors = OutLineTextColor,
                    isError = isLabelEmpty,
                )
                // Description
                OutlinedTextField(
                    value = enteredDescription,
                    modifier = OutLinedTextModifier
                        .height(120.dp),
                    onValueChange = {
                        enteredDescription = it
                        isDescriptionEmpty = it.isEmpty()
                    },
                    label = { Text(text = "Description") },
                    colors = OutLineTextColor,
                    isError = isDescriptionEmpty,
                )
                PickImageFromGallery(activity = ComponentActivity())
                selectpriorityindex=DropDownMenuComponent()
                OutlinedTextField(
                    value = enteredPrice.toString(), onValueChange = { newText -> enteredPrice = newText.toDouble() },
                    label = { Text(text = "Price: ") },
                    placeholder = { Text(text = "Enter price: ") },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done
                    ),
                    colors = OutLineTextColor,
                    modifier = OutLinedTextModifier,
                )
                DateAndTimeField(
                    date = dateselected.value,
                    time = timeselected.value,
                    onDateClick = {
                        calendarState.show()
                    },
                    onTimeClick = {
                        timeState.show()
                    })


                if (dateselected.value != "" && timeselected.value != "") {
                    Log.d("Date",dateselected.value)
                    Log.d("Time",timeselected.value)
                    val formatter = DateTimeFormatterBuilder()
                        .parseCaseInsensitive()
                        .appendPattern("dd/MM/yyyy[ HH:m[:ss]]") // Optionally include time part
                        .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                        .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                        .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
                        .toFormatter()

                    try {
                        // Parse the date and time strings into LocalDateTime objects
                        localdateTime = LocalDateTime.parse(dateselected.value + " " + timeselected.value, formatter)

                        // Now localDateTime contains the combined date and time in the local time zone
                        Log.d("Local Time",localdateTime.toString())
                    } catch (e: Exception) {
                        // Handle the exception (print the stack trace, log, or handle it based on your requirements)
                        e.printStackTrace()
                        // or provide an alternative behavior or error message
                        println("Error parsing date or time: ${e.message}")
                    }

                    ReminderField(dateselected.value, timeselected.value)
                }


                VerifyByLocationCompose(
                    callback = { location ->
                        currentlatitude = location.latitude
                        currentlongitude = location.longitude
                    }
                )
                val ctx = LocalContext.current.applicationContext
                if (currentlatitude!=0.0 && currentlongitude != 0.0) {
                    Toast.makeText(ctx, "Your current location is captured", Toast.LENGTH_LONG)
                        .show()
                }

            }
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 25.dp, vertical = 10.dp),
                elevation = ButtonDefaults.buttonElevation(6.dp),
                onClick = {
                    if (enteredTitle.isEmpty()) {
                        Toast.makeText(ctx, "Please fill the title", Toast.LENGTH_SHORT).show()
                        isTitleEmpty = true
                    } else if (enteredDescription.isEmpty()) {
                        Toast.makeText(ctx, "Please fill the description", Toast.LENGTH_SHORT)
                            .show()
                        isDescriptionEmpty = true
                    } else if (dateselected.value == "" || timeselected.value == "") {
                        Toast.makeText(ctx, "Please select the due date", Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        viewModel.addTodo(Todo(0,enteredTitle,enteredLabel,enteredDescription,currentlatitude,currentlongitude,enteredPrice,LocalDateTime.now(),localdateTime,false,selectpriorityindex))
                        Toast.makeText(ctx, "Entries are added!!", Toast.LENGTH_SHORT).show()
                    }
                },

                shape = MaterialTheme.shapes.small.copy(all = CornerSize(10.dp))
            ) {
                Text(
                    text = "ADD",
                    color = Color.White,
                    modifier = Modifier.padding(vertical = 5.dp)
                )
            }
        }
    }
}

