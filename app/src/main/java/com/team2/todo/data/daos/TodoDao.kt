package com.team2.todo.data.daos

import androidx.room.Dao
import androidx.room.Insert
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
    suspend fun upsertTodo(todoEntity: Todo): Long

    @Query("SELECT * FROM todos where todoId = :todoId")
    fun getTodoWithSubTodosBasedOnTodoId(todoId: Long): Flow<List<TodoWithSubTodos>>

    @Query("SELECT * FROM todos where status = :status")
    fun getAllTodosWithSubTodos(status: Boolean): Flow<List<TodoWithSubTodos>>

    @Query("SELECT * FROM todos ORDER BY priority ASC")
    fun getAllTodosOrderedByPriorityASCWithSubTodos(): Flow<List<TodoWithSubTodos>>

    @Query("SELECT * FROM todos ORDER BY priority DESC")
    fun getAllTodosOrderedByPriorityDESCWithSubTodos(): Flow<List<TodoWithSubTodos>>

    @Transaction
    @Query("UPDATE todos SET status = :status WHERE todoId = :todoId")
    suspend fun updateTodoStatus(todoId: Long, status: Boolean)

    @Query("SELECT * FROM images WHERE todoId = :todoId")
    fun getAllTodoImagesBasedOnTodo(todoId: Long): Flow<List<Images>>

    @Insert
    suspend fun insertImage(imageEntity: Images)

    @Query("SELECT * FROM todos ORDER BY price ASC")
    fun getAllTodosOrderedByPriceASCWithSubTodos(): Flow<List<TodoWithSubTodos>>

    @Query("SELECT * FROM todos ORDER BY price DESC")
    fun getAllTodosOrderedByPriceDESCWithSubTodos(): Flow<List<TodoWithSubTodos>>


}