package com.team2.todo.data.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity("images",
    foreignKeys = [
        ForeignKey(
            entity = Todo::class,
            parentColumns = ["todoId"],
            childColumns = ["todoId"],
            onDelete = ForeignKey.CASCADE
        )
    ])
data class Images(
    @PrimaryKey(autoGenerate = true)
    val imageId: Int,
    val imagePath: String?,
    val todoId: Int?
)
