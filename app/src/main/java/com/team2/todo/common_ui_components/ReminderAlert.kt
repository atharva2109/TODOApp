package com.team2.todo.common_ui_components
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.team2.todo.R
import com.team2.todo.data.entities.relations.TodoWithSubTodos
import com.team2.todo.ui.theme.PrimaryColor
import com.team2.todo.utils.NavigationUtil
import com.team2.todo.utils.Screen

/**
 * Created by Manu KJ on 11/9/23.
 */
class ReminderModel(val totalCount: Int, val property: TodoWithSubTodos)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun ReminderAlertCompose(
    title: String = "Diamond Building",
    description: String = "Step into a world of charm and functionality as you explore this exquisite property. The meticulously landscaped grounds welcome you, creating a sense of serenity from the moment you arrive. Inside, discover an elegant fusion of modern design and timeless appeal, with each room thoughtfully curated for comfort and style. From the inviting living spaces to the well-appointed amenities, this property offers a seamless blend of luxury and practicality, inviting you to envision a life surrounded by sophistication. Embrace the potential of a new chapter as you wander through the inviting spaces and imagine the possibilities of calling this property your own."
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(bottom = 25.dp, start = 2.dp, end = 2.dp)
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
            maxLines = 3,
            color = PrimaryColor,
            textAlign = TextAlign.Justify,
            fontSize = 15.sp,
            modifier = Modifier.padding(horizontal = 10.dp),
            overflow = TextOverflow.Ellipsis
        )
        Button(
            onClick = {
                NavigationUtil.navigateTo(Screen.DetailsScreen)
            },
            modifier = Modifier
                .padding(vertical = 10.dp, horizontal = 10.dp)
                .fillMaxWidth()
        ) {
            Text("Inspect & Complete Task")
        }

    }

}