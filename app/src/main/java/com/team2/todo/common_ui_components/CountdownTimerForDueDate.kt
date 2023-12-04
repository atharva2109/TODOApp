package com.team2.todo.common_ui_components
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import com.team2.todo.utils.AppUtil
import kotlinx.coroutines.delay
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

@Composable
fun CountdownTimerForDueDate(
    dueDateTime: LocalDateTime,
): String {
    val currentDateTime = LocalDateTime.now()
    val timeDifferenceInMillis = ChronoUnit.MILLIS.between(currentDateTime, dueDateTime)
    val remainingTime =
        remember { mutableLongStateOf(timeDifferenceInMillis) }

    LaunchedEffect(remainingTime) {
        while (remainingTime.value > 0) {
            delay(1000L)
            remainingTime.value -= 1000L
        }
    }
    if(remainingTime.value <= 0)
    {
        return AppUtil.OVERDUE
    }
    val daysRemaining = remainingTime.value / (1000 * 60 * 60 * 24)
    val hoursRemaining = (remainingTime.value % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60)
    val minutesRemaining = (remainingTime.value % (1000 * 60 * 60)) / (1000 * 60)
    val secondsRemaining = (remainingTime.value % (1000 * 60)) / 1000
    return "$daysRemaining d : $hoursRemaining h : $minutesRemaining m : $secondsRemaining s"
}