import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import com.team2.todo.data.datautils.LocalDatetimeToWords
import com.team2.todo.screens.subtodo_details.ui_components.DisplaySubTodoImage
import com.team2.todo.screens.subtodo_details.view_model.SubTodoDetailsViewModel

@Composable
fun SubTodoDetailsComponent(viewModel: SubTodoDetailsViewModel, subTodoId: Long) {

    viewModel.getSubTodoById(subTodoId)
    val subTodoState by remember { viewModel.subTodo }.collectAsState()
    Scaffold { padding ->
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(700.dp),
                elevation = CardDefaults.cardElevation(10.dp),
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
                    subTodoState?.title?.let {
                        Text(
                            text = it,
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.titleLarge,
                            modifier = Modifier.padding(5.dp),
                            color = MaterialTheme.colorScheme.onPrimaryContainer

                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        AssistChip(
                            onClick = { },
                            label = {
                                if (subTodoState?.priority == 1) {
                                    Text("High")

                                }
                                if (subTodoState?.priority == 2) {
                                    Text("Medium")

                                }
                                if (subTodoState?.priority == 3) {
                                    Text("Low")

                                }
                            },
                            leadingIcon = {
                                if (subTodoState?.priority == 1) {
                                    Icon(
                                        imageVector = Icons.Filled.AccountCircle,
                                        contentDescription = "Localized description",
                                        Modifier.size(AssistChipDefaults.IconSize),
                                        tint = Color.Red
                                    )
                                }
                                if (subTodoState?.priority == 2) {
                                    Icon(
                                        imageVector = Icons.Filled.AccountCircle,
                                        contentDescription = "Localized description",
                                        Modifier.size(AssistChipDefaults.IconSize),
                                        tint = Color.Yellow
                                    )
                                }
                                if (subTodoState?.priority == 3) {
                                    Icon(
                                        imageVector = Icons.Filled.AccountCircle,
                                        contentDescription = "Localized description",
                                        Modifier.size(AssistChipDefaults.IconSize),
                                        tint = Color.LightGray
                                    )
                                }
                            }

                        )
                        Text(
                            text = LocalDatetimeToWords.formatLocalDateTimeAsWords(subTodoState?.dueDate),
                            style = MaterialTheme.typography.titleSmall,
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                            textAlign = TextAlign.Start
                        )
                    }


                    Spacer(modifier = Modifier.height(8.dp))
                    DisplaySubTodoImage(subTodoState?.imagePath)
                    Spacer(modifier = Modifier.height(12.dp))

                    subTodoState?.description?.let {
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

