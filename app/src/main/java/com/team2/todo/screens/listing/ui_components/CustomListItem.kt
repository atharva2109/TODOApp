package com.team2.todo.screens.listing.ui_components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.team2.todo.data.entities.relations.TodoWithSubTodos

/**
 * Created by Manu KJ on 11/15/23.
 */

@Composable
fun CustomListItem(property: TodoWithSubTodos) {
    Column {
        Text(text = property.todo.title)
        LazyColumn( modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)) {
            items(count = property.subtodos.size) { index ->
                var subTodo = property.subtodos[index]
                Text(text = subTodo.title.toString(), modifier = Modifier.padding(10.dp))
            }
        }
    }
}