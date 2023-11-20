package com.team2.todo.screens.add_todo

import android.location.Location
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

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold

import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.team2.todo.common_ui_components.location.VerifyByLocationCompose
import com.team2.todo.data.RealEstateDatabase
import com.team2.todo.data.daos.TodoDao
import com.team2.todo.data.entities.Images
import com.team2.todo.data.entities.Todo
import com.team2.todo.data.repo.TodoRepo
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.team2.todo.R
import com.team2.todo.common_ui_components.location.VerifyByLocationCompose
import com.team2.todo.data.entities.SubTodo
import com.team2.todo.data.repo.SubTodoRepo
import com.team2.todo.screens.add_todo.ui_components.AddEditAppBar
import com.team2.todo.screens.add_todo.ui_components.DateAndTimeField
import com.team2.todo.screens.add_todo.ui_components.DatePickerComponent
import com.team2.todo.screens.add_todo.ui_components.DropDownMenuComponent
import com.team2.todo.screens.add_todo.ui_components.PickImageForSubTodo
import com.team2.todo.screens.add_todo.ui_components.PickImagesForTodo
import com.team2.todo.screens.add_todo.ui_components.ReminderField
import com.team2.todo.screens.add_todo.ui_components.TimePickerComponent
import com.team2.todo.screens.add_todo.view_model.AddSubTodoViewModel
import com.team2.todo.screens.add_todo.view_model.AddTodoViewModel
import com.team2.todo.ui.theme.PrimaryColor
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatterBuilder
import java.time.format.DateTimeParseException
import java.time.temporal.ChronoField
import com.team2.todo.utils.LocationUtils
import java.text.SimpleDateFormat
import java.util.Locale

/**
 * Created by Atharva K on 11/13/23.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTodos(isSubTodo:Boolean=false,todoid:Long=0) {
    val OutLineTextColor = OutlinedTextFieldDefaults.colors(
        focusedBorderColor = Color.Black,
        unfocusedBorderColor = PrimaryColor,
        focusedLabelColor = Color.Black,
        disabledLabelColor = PrimaryColor
    )
    val OutLinedTextModifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 5.dp)

    var ctx = LocalContext.current
    var db = RealEstateDatabase.getInstance(ctx)
    var repository = TodoRepo(db)
    var viewModel = AddTodoViewModel(repository)

    var subtodorepo=SubTodoRepo(db)
    var subtodviewmodel=AddSubTodoViewModel(subtodorepo)

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
    var imageUris: List<String> = mutableListOf()

    var imageUri:String=""

    var localdateTime: LocalDateTime = LocalDateTime.now()

    //Getting TodoId from Todos Table
    val scope = rememberCoroutineScope()
    var todoIdretrieved by remember { mutableStateOf<Long?>(null) }
    var todoIdretrievalInProgress by remember { mutableStateOf(false) }


    var selectpriorityindex by remember {
        mutableStateOf(0)
    }
    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(it)
        ) {
            AddEditAppBar(isSubTodo)
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
                    label = { if(isSubTodo) Text(text = "Subtask Title") else Text(text = "Title")},
                    colors = OutLineTextColor,
                    isError = isTitleEmpty,
                )
                //Label
                if(!isSubTodo) {
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
                }
                // Description
                OutlinedTextField(
                    value = enteredDescription,
                    modifier = OutLinedTextModifier
                        .height(120.dp),
                    onValueChange = {
                        enteredDescription = it
                        isDescriptionEmpty = it.isEmpty()
                    },
                    label = { if(isSubTodo) Text(text = "Subtask Description") else Text(text = "Description") },
                    colors = OutLineTextColor,
                    isError = isDescriptionEmpty,
                )
                if(isSubTodo){
                    var uri=PickImageForSubTodo(activity = ComponentActivity())
                    imageUri=uri.toString()
                }
                else{
                    var listUris = PickImagesForTodo(activity = ComponentActivity())
                    imageUris = listUris.map { it.toString() }
                    Log.d("ImageList", imageUris.toString())
                }

                selectpriorityindex = DropDownMenuComponent()

                if(!isSubTodo) {
                    OutlinedTextField(
                        value = enteredPrice.toString(),
                        onValueChange = { newText -> enteredPrice = newText.toDouble() },
                        label = { Text(text = "Price: ") },
                        placeholder = { Text(text = "Enter price: ") },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Done
                        ),
                        colors = OutLineTextColor,
                        modifier = OutLinedTextModifier,
                    )
                }
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
                    val formatter = DateTimeFormatterBuilder()
                        .parseCaseInsensitive()
                        .appendPattern("dd/MM/yyyy[ HH:m[:ss]]")
                        .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                        .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                        .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
                        .toFormatter()

                    try {
                        localdateTime = LocalDateTime.parse(
                            dateselected.value + " " + timeselected.value,
                            formatter
                        )
                        Log.d("Local Time", localdateTime.toString())
                    } catch (e: Exception) {
                        e.printStackTrace()
                        println("Error parsing date or time: ${e.message}")
                    }

                    ReminderField(dateselected.value, timeselected.value)
                }
                if(!isSubTodo){
                    VerifyByLocationCompose(
                        callback = { location ->
                            currentlatitude = location.latitude
                            currentlongitude = location.longitude
                        }
                    )
                    val ctx = LocalContext.current.applicationContext
                    if (currentlatitude != 0.0 && currentlongitude != 0.0) {
                        Toast.makeText(ctx, "Your current location is captured", Toast.LENGTH_LONG)
                            .show()
                    }
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
                        if(isSubTodo){
                            subtodviewmodel.addSubTodo(SubTodo(0,todoid,enteredTitle,enteredDescription,imageUri,
                                LocalDateTime.now(),localdateTime,false,selectpriorityindex))
                            Toast.makeText(ctx, "SubTodo added successfully", Toast.LENGTH_SHORT)
                                .show()
                        }
                        else{
                            scope.launch {
                                try {
                                    todoIdretrievalInProgress = true
                                    todoIdretrieved = viewModel.addTodo(
                                        Todo(
                                            0,
                                            enteredTitle,
                                            enteredLabel,
                                            enteredDescription,
                                            currentlatitude,
                                            currentlongitude,
                                            enteredPrice,
                                            LocalDateTime.now(),
                                            localdateTime,
                                            false,
                                            selectpriorityindex
                                        )
                                    )
                                    todoIdretrievalInProgress = false
                                    todoIdretrieved?.let { todoId ->
                                        for (stringValue in imageUris) {
                                            viewModel.addImage(Images(0, stringValue, todoId))
                                        }
                                        Toast.makeText(
                                            ctx,
                                            "Added todo successfully",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    } ?: run {
                                        Toast.makeText(ctx, "Error adding Todo", Toast.LENGTH_SHORT)
                                            .show()
                                    }

                                } catch (e: Exception) {
                                    e.printStackTrace()
                                    Toast.makeText(ctx, "Error adding Todo", Toast.LENGTH_SHORT).show()
                                }
                            }
                        }

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

