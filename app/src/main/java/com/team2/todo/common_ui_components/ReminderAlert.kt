package com.team2.todo.common_ui_components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.team2.todo.R
import com.team2.todo.ui.theme.PrimaryColor

/**
 * Created by Manu KJ on 11/9/23.
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun ReminderAlertCompose(
    title: String = "Diamond Building",
    description: String = "description of the current task so its good to finish it as soon as possible "
) {
    return ModalDrawerSheet {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Reminder for $title task",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Image(
                painter = painterResource(id = R.drawable.ic_reminder),
                contentDescription = "reminder",
                modifier = Modifier.size(150.dp)
            )
            Text(
                text = description,
                maxLines = 2,
                color = PrimaryColor,
                textAlign = TextAlign.Center
            )
        }
    }
}