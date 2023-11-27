package com.team2.todo.screens.details_page.ui_components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team2.todo.data.datautils.LocalDatetimeToWords
import com.team2.todo.data.entities.SubTodo
import com.team2.todo.data.entities.Todo
import com.team2.todo.data.entities.relations.TodoWithSubTodos


@Composable
fun TodosCard(todos: SubTodo,/* dateTime: String*/) {
    var checkedState by remember { mutableStateOf(todos.status) }

    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp,

            ),
        modifier = Modifier
            .fillMaxWidth().clip(CircleShape).padding(15.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    )
    {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(7.dp),
            horizontalArrangement = Arrangement.SpaceBetween

        )
        {

            checkedState?.let {
                Checkbox(
                    checked = it,
                    onCheckedChange = { checkedState = it }
                )
            }

            todos.title?.let {
                Text(
                    text = it,
                    modifier = Modifier
                        .padding(10.dp),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleMedium

                )
            }
            FilledTonalIconButton(
                onClick = {
                }
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "SubTodo")
            }
        }


    }
}


@Composable
fun TodosCardMain(todos: TodoWithSubTodos, dateTime: String) {
    var checkedState by remember { mutableStateOf(false) }

    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp,

            ),
        modifier = Modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onTertiary
        )
    )
    {

        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween

        )
        {


            Switch(
                checked = checkedState,
                onCheckedChange = {
                    checkedState = it
                },

                thumbContent = if (checkedState) {
                    {
                        Icon(
                            imageVector = Icons.Filled.Check,
                            contentDescription = null,
                            modifier = Modifier.size(SwitchDefaults.IconSize),
                        )
                    }

                } else {
                    null
                }
            )
            Text(
                text = todos.todo.title,
                modifier = Modifier
                    .padding(16.dp),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleMedium

            )
            Text(
                text = todos.todo.label!!,
                modifier = Modifier
                    .padding(16.dp),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleSmall

            )
            FilledTonalIconButton(
                onClick = {
                }
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "SubTodo")
            }
        }


    }
}


