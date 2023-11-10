@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)

package com.team2.todo.screens.details_page

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.team2.todo.R
import com.team2.todo.data.entities.SubTodo
import com.team2.todo.screens.details_page.ui_components.DummyCompose
import com.team2.todo.screens.details_page.ui_components.TodosCard
import java.time.LocalDateTime


@Preview
@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun DetailsPage() {
    val dummy_images = listOf(
        "",
    )

    var subTaskList = mutableListOf<SubTodo>();

    subTaskList.add(SubTodo(1,1,"SubTask1", "Maintain Property", LocalDateTime.now(), LocalDateTime.now(), true,2))
    subTaskList.add(SubTodo(2,1,"SubTask2", "Inspect Property", LocalDateTime.now(), LocalDateTime.now(), true,1))
    subTaskList.add(SubTodo(1,1,"SubTask1", "Maintain Property", LocalDateTime.now(), LocalDateTime.now(), true,2))
    subTaskList.add(SubTodo(2,1,"SubTask2", "Inspect Property", LocalDateTime.now(), LocalDateTime.now(), true,1))
    subTaskList.add(SubTodo(1,1,"SubTask1", "Maintain Property", LocalDateTime.now(), LocalDateTime.now(), true,2))
    subTaskList.add(SubTodo(2,1,"SubTask2", "Inspect Property", LocalDateTime.now(), LocalDateTime.now(), true,1))
    subTaskList.add(SubTodo(1,1,"SubTask1", "Maintain Property", LocalDateTime.now(), LocalDateTime.now(), true,2))
    subTaskList.add(SubTodo(2,1,"SubTask2", "Inspect Property", LocalDateTime.now(), LocalDateTime.now(), true,1))
    subTaskList.add(SubTodo(1,1,"SubTask1", "Maintain Property", LocalDateTime.now(), LocalDateTime.now(), true,2))
    subTaskList.add(SubTodo(2,1,"SubTask2", "Inspect Property", LocalDateTime.now(), LocalDateTime.now(), true,1))
    subTaskList.add(SubTodo(1,1,"SubTask1", "Maintain Property", LocalDateTime.now(), LocalDateTime.now(), true,2))
    subTaskList.add(SubTodo(2,1,"SubTask2", "Inspect Property", LocalDateTime.now(), LocalDateTime.now(), true,1))
    subTaskList.add(SubTodo(1,1,"SubTask1", "Maintain Property", LocalDateTime.now(), LocalDateTime.now(), true,2))
    subTaskList.add(SubTodo(2,1,"SubTask2", "Inspect Property", LocalDateTime.now(), LocalDateTime.now(), true,1))
    subTaskList.add(SubTodo(1,1,"SubTask1", "Maintain Property", LocalDateTime.now(), LocalDateTime.now(), true,2))
    subTaskList.add(SubTodo(2,1,"SubTask2", "Inspect Property", LocalDateTime.now(), LocalDateTime.now(), true,1))
    subTaskList.add(SubTodo(1,1,"SubTask1", "Maintain Property", LocalDateTime.now(), LocalDateTime.now(), true,2))
    subTaskList.add(SubTodo(2,1,"SubTask2", "Inspect Property", LocalDateTime.now(), LocalDateTime.now(), true,1))
    subTaskList.add(SubTodo(1,1,"SubTask1", "Maintain Property", LocalDateTime.now(), LocalDateTime.now(), true,2))
    subTaskList.add(SubTodo(2,1,"SubTask2", "Inspect Property", LocalDateTime.now(), LocalDateTime.now(), true,1))

    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f
    ) {
        dummy_images.size
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Main TODO Title", Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
                }
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .padding(it)
                    .padding(start = 10.dp),
            ) {
                Text(text = "Description : ###########")

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

                Spacer(modifier = Modifier.padding(top = 15.dp))

                Text(text = "Price: ###########")

                Spacer(modifier = Modifier.padding(top = 15.dp))

                Text(text = "Location: ###########")

                Spacer(modifier = Modifier.padding(top = 15.dp))

                Text(text = "Priority: ###########")

                Spacer(modifier = Modifier.padding(top = 15.dp))

                Text(text = "Reminder: ###########")

                Spacer(modifier = Modifier.padding(top = 15.dp))

                Text(text = "Due Date: ###########")

                Spacer(modifier = Modifier.padding(top = 20.dp))

                Box() {
                    Text(text = "Sub Tasks ", fontSize = 20.sp, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())

                }
                Box {
                    LazyColumn() {
                        items(subTaskList) { subTask ->
                            //subTask.title?.let { it1 -> Text(text = it1, Modifier.padding(15.dp)) }
                            TodosCard(todos = subTask)
                        }
                    }
                }





            }

        }
    )
}