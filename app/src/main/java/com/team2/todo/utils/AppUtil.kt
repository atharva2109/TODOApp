package com.team2.todo.utils

import androidx.compose.ui.graphics.Color
import com.team2.todo.ui.theme.PriorityHigh
import com.team2.todo.ui.theme.PriorityLow
import com.team2.todo.ui.theme.PriorityMedium

object AppUtil {
    fun getPriorityString(priorityIndex: Int): String {
        var priority = "Low";
        if (priorityIndex == 1) {
            priority = "Medium"
        }
        if (priorityIndex == 2) {
            priority = "High"
        }
        return priority;
    }

    fun getPriorityColor(priorityIndex: Int): Color {
        var color = PriorityLow
        if (priorityIndex == 1) {
            color = PriorityMedium
        }
        if (priorityIndex == 2) {
            color = PriorityHigh
        }
        return color
    }
}