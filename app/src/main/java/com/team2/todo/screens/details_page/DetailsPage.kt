@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)

package com.team2.todo.screens.details_page

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.team2.todo.data.RealEstateDatabase
import com.team2.todo.data.entities.SubTodo
import com.team2.todo.data.entities.Todo
import com.team2.todo.data.entities.relations.TodoWithSubTodos
import com.team2.todo.data.repo.SubTodoRepo
import com.team2.todo.data.repo.TodoRepo
import com.team2.todo.screens.details_page.ui_components.TodosCard
import com.team2.todo.screens.details_page.view_model.DetailsPageViewModel
import com.team2.todo.utils.NavigationUtil
import com.team2.todo.utils.Screen
import java.time.LocalDateTime



@Preview
@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun DetailsPage() {


    val todoContext = LocalContext.current
    val database = RealEstateDatabase.getInstance(todoContext)
    val todoRepo = TodoRepo(database)
    val subTodoRepo = SubTodoRepo(database)
    val viewModel = DetailsPageViewModel(todoRepo, subTodoRepo)

    var subTodoId by remember { mutableIntStateOf(0) }

    var subTaskList = mutableListOf<SubTodo>();

    subTaskList.add(SubTodo(1,1,"SubTask1", "Maintain Property", LocalDateTime.now(), LocalDateTime.now(), true,2))
    subTaskList.add(SubTodo(2,1,"SubTask2", "Inspect Property", LocalDateTime.now(), LocalDateTime.now(), false,1))
    subTaskList.add(SubTodo(1,1,"SubTask1", "Maintain Property", LocalDateTime.now(), LocalDateTime.now(), true,2))
    subTaskList.add(SubTodo(2,1,"SubTask2", "Inspect Property", LocalDateTime.now(), LocalDateTime.now(), true,1))
    subTaskList.add(SubTodo(1,1,"SubTask1", "Maintain Property", LocalDateTime.now(), LocalDateTime.now(), false,2))
    subTaskList.add(SubTodo(2,1,"SubTask2", "Inspect Property", LocalDateTime.now(), LocalDateTime.now(), true,1))
    subTaskList.add(SubTodo(1,1,"SubTask1", "Maintain Property", LocalDateTime.now(), LocalDateTime.now(), true,2))
    subTaskList.add(SubTodo(2,1,"SubTask2", "Inspect Property", LocalDateTime.now(), LocalDateTime.now(), false,1))
    subTaskList.add(SubTodo(1,1,"SubTask1", "Maintain Property", LocalDateTime.now(), LocalDateTime.now(), true,2))
    subTaskList.add(SubTodo(2,1,"SubTask2", "Inspect Property", LocalDateTime.now(), LocalDateTime.now(), true,1))
    subTaskList.add(SubTodo(1,1,"SubTask1", "Maintain Property", LocalDateTime.now(), LocalDateTime.now(), true,2))
    subTaskList.add(SubTodo(2,1,"SubTask2", "Inspect Property", LocalDateTime.now(), LocalDateTime.now(), false,1))
    subTaskList.add(SubTodo(1,1,"SubTask1", "Maintain Property", LocalDateTime.now(), LocalDateTime.now(), true,2))
    subTaskList.add(SubTodo(2,1,"SubTask2", "Inspect Property", LocalDateTime.now(), LocalDateTime.now(), true,1))
    subTaskList.add(SubTodo(1,1,"SubTask1", "Maintain Property", LocalDateTime.now(), LocalDateTime.now(), true,2))
    subTaskList.add(SubTodo(2,1,"SubTask2", "Inspect Property", LocalDateTime.now(), LocalDateTime.now(), true,1))
    subTaskList.add(SubTodo(1,1,"SubTask1", "Maintain Property", LocalDateTime.now(), LocalDateTime.now(), true,2))
    subTaskList.add(SubTodo(2,1,"SubTask2", "Inspect Property", LocalDateTime.now(), LocalDateTime.now(), true,1))
    subTaskList.add(SubTodo(1,1,"SubTask1", "Maintain Property", LocalDateTime.now(), LocalDateTime.now(), true,2))
    subTaskList.add(SubTodo(2,1,"SubTask2", "Inspect Property", LocalDateTime.now(), LocalDateTime.now(), true,1))


   // val todos by viewModel.todoListBasedOnId.collectAsState(initial = emptyList())
    var mainTask = Todo(todoId = 1, title = "Main Task One", status = false, priority = 2, longitude = 54.0, latitude = 55.0, label = "Maintenance", dueDate = LocalDateTime.now(), createdDate = LocalDateTime.now(), description = "Property in Lahore")
    var mainTaskOne = TodoWithSubTodos(todo = Todo(todoId = 1, title = "Main Task One", status = false, priority = 2, longitude = 54.0, latitude = 55.0, label = "Maintenance", dueDate = LocalDateTime.now(), createdDate = LocalDateTime.now(), description = "Property in Lahore"), subtodos = subTaskList, images = emptyList() )



    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = mainTask.title, Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
                }
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .padding(it)
                    .padding(start = 10.dp),
            ) {
                Text(text = "Description :" + mainTask.description)

                Spacer(modifier = Modifier.padding(top = 30.dp))

//                Box(){
//                    HorizontalPager(state = pagerState, key = {dummy_images[2]}) {
//                        index ->
//                        Image(
//                            painter = painterResource(id = dummy_images[index]),
//                            contentDescription = null,
//                            contentScale = ContentScale.Crop,
//
//                        )
//                    }
//                }

                Spacer(modifier = Modifier.padding(top = 20.dp))

                Box {
                    if(mainTask.latitude == null && mainTask.longitude == null){
                        Text(text = "Verification Status: Not Verified")
                    }else{
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
                    Text(text = "Priority: ###########")

                    Spacer(modifier = Modifier.padding(top = 20.dp))
                }
                

                Box {
                    Text(text = "Due Date: ###########")

                    Box {
                        Spacer(modifier = Modifier.padding(top = 20.dp))
                    }
                }
                Box(
                    Modifier
                        .fillMaxWidth()
                        .padding(end = 15.dp),
                    contentAlignment = Alignment.BottomEnd) {
                    SmallFloatingActionButton(
                        onClick = {
                            viewModel.addTodo(mainTask)
                        },
                        containerColor = MaterialTheme.colorScheme.secondaryContainer,
                        contentColor = MaterialTheme.colorScheme.secondary
                    ) {
                        Icon(Icons.Filled.Add, "Create")
                    }
                }
                Box(
                    Modifier
                        .fillMaxWidth()
                        .padding(end = 15.dp),
                    contentAlignment = Alignment.BottomEnd) {
                    SmallFloatingActionButton(
                        onClick = {
                            viewModel.addSubTodos(subTaskList[0])
                            viewModel.addSubTodos(subTaskList[1])
                        },
                        containerColor = MaterialTheme.colorScheme.secondaryContainer,
                        contentColor = MaterialTheme.colorScheme.secondary
                    ) {
                        Icon(Icons.Filled.Add, "Create")
                    }
                }

                Box() {
                    Text(text = "Sub Tasks ", fontSize = 20.sp, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())

                }
                Box {
                    LazyColumn() {
                        items(subTaskList) { subTask ->
                            //subTask.title?.let { it1 -> Text(text = it1, Modifier.padding(15.dp)) }
                            Box(Modifier.clickable {
                                Log.e(subTask.subTodoId.toString(), subTask.subTodoId.toString())

                                subTodoId = subTask.subTodoId

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