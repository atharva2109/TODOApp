@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)

package com.team2.todo.screens.details_page

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.team2.todo.common_ui_components.CommonAppBar
import com.team2.todo.common_ui_components.ImageLoader
import com.team2.todo.data.RealEstateDatabase
import com.team2.todo.data.entities.SubTodo
import com.team2.todo.data.entities.Todo
import com.team2.todo.data.entities.relations.TodoWithSubTodos
import com.team2.todo.data.repo.SubTodoRepo
import com.team2.todo.data.repo.TodoRepo
import com.team2.todo.screens.details_page.ui_components.TodosCard
import com.team2.todo.screens.details_page.view_model.DetailsPageViewModel
import com.team2.todo.ui.theme.GreyColor
import com.team2.todo.utils.NavigationUtil
import com.team2.todo.utils.Screen
import java.time.LocalDateTime


@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun DetailsPage(todoId: Long) {


    val todoContext = LocalContext.current
    val database = RealEstateDatabase.getInstance(todoContext)
    val todoRepo = TodoRepo(database)
    val subTodoRepo = SubTodoRepo(database)
    val viewModel = DetailsPageViewModel(todoRepo, subTodoRepo)

    var subTodoId by remember { mutableIntStateOf(0) }

    var subTaskList = mutableListOf<SubTodo>();


    // val todos by viewModel.todoListBasedOnId.collectAsState(initial = emptyList())
    var mainTask = Todo(
        todoId = 1,
        title = "Main Task One",
        status = false,
        priority = 2,
        longitude = 54.0,
        latitude = 55.0,
        label = "Maintenance",
        dueDate = LocalDateTime.now(),
        createdDate = LocalDateTime.now(),
        price = 45.0,
        description = "Property in Lahore"
    )


    val collectedTodo by viewModel.getPropertyFromId(todoId).collectAsState(initial = emptyList())
    val collecetedImages by viewModel.getTodoImages(todoId).collectAsState(initial = emptyList())




    if (collectedTodo.isEmpty()) {
        Text(text = "loading")
    } else {
        val propertyDetails: TodoWithSubTodos = collectedTodo[0];

        var checkedState by remember { mutableStateOf(propertyDetails.todo.status) }
        Scaffold(
            floatingActionButton = {
                ExtendedFloatingActionButton(
                    onClick = { NavigationUtil.navigateTo("${Screen.AddOrEditSubToDo.name}/${todoId}") },
                    icon = { Icon(Icons.Filled.AddCircle, "Extended floating action button.") },
                    text = { Text(text = "Add New Task") },
                )
            },
            topBar = {
                CommonAppBar(
                    text = propertyDetails.todo.title,
                    actions = {
                        Icon(
                            Icons.Filled.Edit,
                            "Extended floating action button.",
                            tint = GreyColor,
                            modifier = Modifier
                                .border(
                                    2.dp,
                                    GreyColor,
                                    shape = RoundedCornerShape(8.dp)
                                )
                                .padding(8.dp)
                                .clickable {
                                    //TODO handle navigation to edit

                                }
                        )
                    },
                )
            },
            content = {
                Column(
                    modifier = Modifier
                        .padding(it)
                        .padding(horizontal = 25.dp),
                ) {
                    Text(text = "Description :" + propertyDetails.todo.description)

                    Spacer(modifier = Modifier.padding(top = 30.dp))


                    Box(Modifier.fillMaxWidth()) {
                        collecetedImages.map { bitmap -> bitmap.image }
                            ?.let { it1 -> ImageLoader(bitmapList = it1) }
                    }



                    Spacer(modifier = Modifier.padding(top = 20.dp))

                    Box {
                        if (propertyDetails.todo.longitude == null && propertyDetails.todo.latitude == null) {
                            Text(text = "Verification Status: Not Verified")
                        } else {
                            Row {
                                Text(text = "Verification Status: ")
                                Image(
                                    imageVector = Icons.Default.Check, // or use Icons.Default.Check
                                    contentDescription = null, // Provide a suitable description
                                    modifier = Modifier.size(15.dp, 15.dp),
                                    contentScale = ContentScale.Fit,
                                )
                            }
                        }

                        Spacer(modifier = Modifier.padding(top = 20.dp))
                    }

                    Box {
                        Text(text = "Priority: " + propertyDetails.todo.priority)

                        Spacer(modifier = Modifier.padding(top = 20.dp))
                    }


                    Box {
                        Text(text = "Due Date: " + propertyDetails.todo.dueDate)

                        Box {
                            Spacer(modifier = Modifier.padding(top = 20.dp))
                        }
                    }

                    Checkbox(
                        checked = checkedState!!,
                        onCheckedChange = {
                            checkedState = !checkedState!!
                            viewModel.updateTodo(propertyDetails.todo.todoId, checkedState!!)
                            NavigationUtil.navigateTo(Screen.MainScreen)
                        }
                    )

                    Box() {
                        Text(
                            text = "Sub Tasks ",
                            fontSize = 20.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )

                    }
                    Box {
                        LazyColumn() {
                            items(propertyDetails.subtodos) { subTask ->
                                //subTask.title?.let { it1 -> Text(text = it1, Modifier.padding(15.dp)) }
                                Box(Modifier.clickable {
                                    Log.e(
                                        subTask.subTodoId.toString(),
                                        subTask.subTodoId.toString()
                                    )

                                    subTodoId = subTask.subTodoId.toInt()

                                    NavigationUtil.navigateTo("${Screen.SubTodoDetails.name}/${subTodoId}")
                                }) {
                                    TodosCard(todos = subTask)
                                }
                            }
                        }
                    }


                }

            }
        )
    }
}
