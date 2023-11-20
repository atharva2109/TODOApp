package com.team2.todo.data.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(
    "subtodos",
    foreignKeys = [
        ForeignKey(
            entity = Todo::class,
            parentColumns = ["todoId"],
            childColumns = ["todoId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)


data class SubTodo(
    @PrimaryKey(autoGenerate = true)
    val subTodoId: Int,
    val todoId: Int,
    val title: String?,
    val description: String?,
    /* added type converter for complex date object */
    val createdDate: LocalDateTime?,
    val dueDate: LocalDateTime?,
    val status: Boolean?,
    val priority: Int?,
    val isCompleted:Boolean = false

)





