

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.team2.todo.data.RealEstateDatabase
import com.team2.todo.data.entities.SubTodo
import com.team2.todo.data.entities.Todo
import com.team2.todo.data.entities.relations.TodoWithSubTodos
import com.team2.todo.data.repo.SubTodoRepo
import com.team2.todo.data.repo.TodoRepo
import com.team2.todo.screens.details_page.view_model.DetailsPageViewModel
import com.team2.todo.screens.sub_task_details_page.view_model.SubDetailsPageViewModel
import com.team2.todo.utils.NavigationUtil
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import java.time.LocalDateTime

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun SubTaskDetailsPage(id: Long) {


    val todoContext = LocalContext.current
    val database = RealEstateDatabase.getInstance(todoContext)
    val subTodoRepo = SubTodoRepo(database)
    val viewModel = SubDetailsPageViewModel(subTodoRepo, id)




    val collectedSubTodo by viewModel.subTodo.collectAsState(initial = null)


    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Subtask: " + (collectedSubTodo?.title ?: collectedSubTodo), Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
                }
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .padding(it)
                    .padding(start = 10.dp),
            ) {
                Text(text = "Description : " + collectedSubTodo?.description  )

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

//                Box {
//                    if(mainTask.latitude == null && mainTask.longitude == null){
//                        Text(text = "Verification Status: Not Verified")
//                    }else{
//                        Row {
//                            Text(text = "Verification Status: ")
//                            Image(
//                                imageVector = Icons.Default.Check, // or use Icons.Default.Check
//                                contentDescription = null, // Provide a suitable description
//                                modifier = Modifier.size(15.dp, 15.dp),
//                                contentScale = ContentScale.Fit,
//                            )
//                        }
//                    }
//
//                    Spacer(modifier = Modifier.padding(top = 20.dp))
//                }

                Box {
                    Text(text = "Priority: " + collectedSubTodo?.priority)

                    Spacer(modifier = Modifier.padding(top = 20.dp))
                }


                Box {
                    Text(text = "Due Date: " + collectedSubTodo?.dueDate)

                    Box {
                        Spacer(modifier = Modifier.padding(top = 20.dp))
                    }
                }

            }








        }
    )
}