package com.team2.todo.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity("todos")
data class Todo(
    @PrimaryKey(autoGenerate = true)
    val todoId: Int,
    val title: String,
    val label: String?,
    val description: String,
    val latitude: Double?,
    val longitude: Double?,
    /* added type converter for complex date object */
    val createdDate: LocalDateTime?,
    val dueDate: LocalDateTime?,
    val status: Boolean?,
    val priority: Int?
)
