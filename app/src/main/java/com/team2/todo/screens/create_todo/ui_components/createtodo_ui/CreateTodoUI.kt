package com.team2.todo.screens.create_todo.ui_components.createtodo_ui

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.team2.todo.data.entities.Todo
import com.team2.todo.screens.create_todo.view_model.CreateTodoViewModel
import com.team2.todo.utils.NavigationUtil
import com.team2.todo.utils.Screen
import java.time.LocalDateTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateTodoUI(viewModel: CreateTodoViewModel?) {

    val mContext = LocalContext.current

    var title by remember {
        mutableStateOf("")
    }
    var label by remember {
        mutableStateOf("")
    }
    var description by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = title,
            onValueChange = { lt -> title = lt },
            label = { Text(text = "Title") },
            singleLine = true,
            leadingIcon = {
                Icon(imageVector = Icons.Outlined.Add, contentDescription = "Todo Title")
            }
        )
        OutlinedTextField(
            value = label,
            onValueChange = { lt -> label = lt },
            label = { Text(text = "Label") },
            singleLine = true,
            leadingIcon = {
                Icon(imageVector = Icons.Outlined.AccountBox, contentDescription = "Todo Label")
            }
        )
        OutlinedTextField(
            value = description,
            onValueChange = { lt -> description = lt },
            label = { Text(text = "Description") },
            singleLine = false,
            maxLines = 5,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Info,
                    contentDescription = "Todo Description"
                )
            }

        )
        Button(onClick = {
            viewModel?.addTodo(
                Todo(
                    0, title, label, description,
                     0.00, 0.00, LocalDateTime.now(), LocalDateTime.now(),
                    false, 1
                )
            )
            Toast.makeText(mContext, "Todo Added!", Toast.LENGTH_SHORT).show()
            title = ""
            label = ""
            description = ""
            NavigationUtil.navigateTo(Screen.Listing)


        }
        ) {

            Text(text = "Add")

        }


    }

}
