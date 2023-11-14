package com.team2.todo.screens.add_todo

import android.Manifest
import android.content.Context
import android.os.Build
import android.graphics.Bitmap
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import android.provider.MediaStore
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.*
import androidx.core.content.PermissionChecker
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.core.content.ContextCompat
import androidx.activity.result.contract.ActivityResultContracts.GetContent
import androidx.activity.result.ActivityResultLauncher
import androidx.compose.foundation.layout.Arrangement
import android.graphics.ImageDecoder
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.team2.todo.R
import com.team2.todo.screens.add_todo.ui_components.DatePickerComponent
import com.team2.todo.screens.add_todo.ui_components.DropDownMenuComponent
import com.team2.todo.screens.add_todo.ui_components.PickImageFromGallery
import com.team2.todo.screens.add_todo.ui_components.TimePickerComponent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTodos(){

    var ctx= LocalContext.current.applicationContext
    var enteredTitle by remember {
        mutableStateOf("")
    }
    var enteredDescription by remember {
        mutableStateOf("")
    }

    var enteredPrice by remember {
        mutableStateOf("")
    }

    var isTitleEmpty by remember { mutableStateOf(false) }
    var isDescriptionEmpty by remember { mutableStateOf(false) }

    var (calendarState,dateselected)= DatePickerComponent()
    var (timeState,timeselected)= TimePickerComponent()

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.verticalScroll(rememberScrollState())
        ) {

                TopAppBar(modifier = Modifier.height(40.dp),
                    title = {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Text(text = "ADD TASK", color = Color.White, modifier = Modifier.padding(top = 4.dp))
                        }
                    },

                    colors = TopAppBarDefaults.smallTopAppBarColors(Color.Black),
                )


                OutlinedTextField(

                    value = enteredTitle, onValueChange = { enteredTitle = it
                                    isTitleEmpty=it.isEmpty()
                                                          },
                    label = { Text(text = "Title") },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        textColor = Color.Black
                    ),
                    isError = isTitleEmpty,


                )

                OutlinedTextField(
                    value = enteredDescription,
                    modifier = Modifier
                        .height(200.dp)
                        .padding(10.dp),
                    onValueChange = {enteredDescription = it
                        isDescriptionEmpty=it.isEmpty()
                                    },
                    label = { Text(text = "Description") },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        textColor = Color.Black
                    ),
                    isError = isDescriptionEmpty,
                )


                PickImageFromGallery(activity = ComponentActivity())


                OutlinedTextField(
                    value = enteredPrice, onValueChange = { newText -> enteredPrice = newText },
                    label = { Text(text = "Price: ") },
                    placeholder = { Text(text = "Enter price: ") },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done
                    ),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        textColor = Color.Black
                    )

                )

                DropDownMenuComponent()

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "Due Date")
                    Spacer(modifier = Modifier.padding(4.dp))
                    Button(
                        modifier = Modifier.padding(start = 6.dp),
                        colors = ButtonDefaults.buttonColors(Color.Black),
                        onClick = {calendarState.show()  }) {
                        Icon(imageVector = Icons.Filled.DateRange, contentDescription = "DateTime")

                    }
                    Spacer(modifier = Modifier.padding(6.dp))
                    Button(
                        colors = ButtonDefaults.buttonColors(Color.Black),
                        onClick = { timeState.show()}) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_outline_alarm_24),
                            contentDescription = "TimeClock"
                        )

                    }

                }
                Row {
                    Text(text = "Due on  ${dateselected.value}", fontWeight = FontWeight.Bold)
                    Text(text = " at ${timeselected.value}", fontWeight = FontWeight.Bold)
                }


// fetching local context
            val ctx = LocalContext.current
            Button(
                    modifier = Modifier
                        .width(200.dp)
                        .padding(top = 12.dp),
                    elevation = ButtonDefaults.buttonElevation(6.dp),
                    onClick = {
                        if(enteredTitle.isEmpty() ){
                            Toast.makeText(ctx, "Please fill the title", Toast.LENGTH_SHORT).show()
                            isTitleEmpty=true
                        }
                        else if(enteredDescription.isEmpty() ){
                            Toast.makeText(ctx, "Please fill the description", Toast.LENGTH_SHORT).show()
                            isDescriptionEmpty=true
                        }
                        else if(dateselected.value==""||timeselected.value==""){
                            Toast.makeText(ctx, "Please select the due date", Toast.LENGTH_SHORT).show()
                    }
                        else{
                            Toast.makeText(ctx, "Entries are added!!", Toast.LENGTH_SHORT).show()
                        }
                    },
                    colors = ButtonDefaults.buttonColors(Color.Black),
                    shape = MaterialTheme.shapes.small.copy(all = CornerSize(0.dp))
                ) {
                    Text(text = "ADD", color = Color.White)
                }
            }
        }





