package com.team2.todo.data.daos

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.team2.todo.data.entities.Images
import com.team2.todo.data.entities.Todo
import com.team2.todo.data.entities.relations.TodoWithSubTodos
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {
    @Upsert
    suspend fun upsertTodo(todoEntity: Todo)

    @Query("SELECT * FROM todos where todoId = :todoId")
    fun getTodoWithSubTodosBasedOnTodoId(todoId: Int): Flow<List<TodoWithSubTodos>>

    @Query("SELECT * FROM todos")
    fun getAllTodosWithSubTodos(): Flow<List<TodoWithSubTodos>>

    @Query("SELECT * FROM todos ORDER BY priority ASC")
    fun getAllTodosOrderedByPriorityWithSubTodos(): Flow<List<TodoWithSubTodos>>

    @Transaction
    @Query("UPDATE todos SET status = :status WHERE todoId = :todoId")
    suspend fun updateTodoStatus(todoId: Int, status: Boolean)

    @Query("SELECT * FROM images WHERE todoId = :todoId")
    fun getAllTodoImagesBasedOnTodo(todoId: Int): Flow<List<Images>>


}