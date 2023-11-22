import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.sharp.DateRange
import androidx.compose.material.icons.sharp.Info
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.team2.todo.R
import com.team2.todo.common_ui_components.CommonAppBar
import com.team2.todo.common_ui_components.EmptyList
import com.team2.todo.data.datautils.LocalDatetimeToWords
import com.team2.todo.screens.subtodo_details.ui_components.DisplaySubTodoImage
import com.team2.todo.screens.subtodo_details.view_model.SubTodoDetailsViewModel
import com.team2.todo.ui.theme.GreyColor
import com.team2.todo.utils.NavigationUtil
import com.team2.todo.utils.Screen

@Composable
fun SubTodoDetailsComponent(viewModel: SubTodoDetailsViewModel, subTodoId: Long) {

    viewModel.getSubTodoById(subTodoId)
    val propertySubTaskState by remember { viewModel.subTodo }.collectAsState()
    if (propertySubTaskState == null) {
        EmptyList(title = "No Active Sales Found", drawableID = R.drawable.ic_no_in_sale_list)

    } else {


        Scaffold(
            topBar = {
                propertySubTaskState?.title?.let {
                    CommonAppBar(
                        text = it,
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
                                        //SUB-TODO handle navigation to edit

                                    }
                            )
                        },
                    )
                }
            }) { padding ->


            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(modifier = Modifier.height(20.dp))

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(700.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.onPrimary
                    ),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth()
                    ) {

                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically
                        ) {


                            if (propertySubTaskState?.priority == 1) {
                                Icon(
                                    imageVector = Icons.Sharp.Info,
                                    contentDescription = "Localized description",
                                    modifier = Modifier.size(18.dp),
                                    tint = Color.Blue
                                )
                                Spacer(modifier = Modifier.width(8.dp))

                                Text(
                                    text = "High",
                                    style = MaterialTheme.typography.titleSmall,
                                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                                    textAlign = TextAlign.Start
                                )
                            }
                            if (propertySubTaskState?.priority == 2) {
                                Icon(
                                    imageVector = Icons.Sharp.Info,
                                    contentDescription = "Localized description",
                                    modifier = Modifier.size(18.dp),
                                    tint = Color.Yellow
                                )
                                Spacer(modifier = Modifier.width(8.dp))

                                Text(
                                    text = "Medium",
                                    style = MaterialTheme.typography.titleSmall,
                                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                                    textAlign = TextAlign.Start
                                )
                            }
                            if (propertySubTaskState?.priority == 3) {
                                Icon(
                                    imageVector = Icons.Sharp.Info,
                                    contentDescription = "Localized description",
                                    modifier = Modifier.size(18.dp),
                                    tint = Color.LightGray
                                )
                                Spacer(modifier = Modifier.width(8.dp))

                                Text(
                                    text = "Low",
                                    style = MaterialTheme.typography.titleSmall,
                                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                                    textAlign = TextAlign.Start
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(12.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Icon(
                                imageVector = Icons.Sharp.DateRange,
                                contentDescription = "Localized description",
                                modifier = Modifier.size(18.dp),
                                tint = Color.Blue
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = LocalDatetimeToWords.formatLocalDateTimeAsWords(
                                    propertySubTaskState?.dueDate
                                ),
                                style = MaterialTheme.typography.titleSmall,
                                color = MaterialTheme.colorScheme.onPrimaryContainer,
                                textAlign = TextAlign.Start
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))
                        DisplaySubTodoImage(propertySubTaskState?.image)
                        Spacer(modifier = Modifier.height(12.dp))

                        propertySubTaskState?.description?.let {
                            Text(
                                text = it,
                                style = MaterialTheme.typography.bodyMedium,
                                maxLines = 10,
                                color = MaterialTheme.colorScheme.onPrimaryContainer,
                                textAlign = TextAlign.Justify
                            )
                        }


                    }
                }
            }
        }
    }


}

