package com.team2.todo.data.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.team2.todo.data.entities.SubTodo
import com.team2.todo.data.entities.relations.TodoWithSubTodos
import kotlinx.coroutines.flow.Flow

@Dao
interface SubTodoDao {
    @Upsert
    suspend fun upsertSubTodo(subTodoEntity: SubTodo)

    @Query("SELECT * FROM subtodos where todoId = :todoId")
    fun getSubTodoBasedOnTodo(todoId : Int): Flow<List<SubTodo>>

    @Query("SELECT  * FROM subtodos where todoId = :todoId ORDER BY priority ASC")
    fun getSubTodoOrderedByPriority(todoId : Int): Flow<List<SubTodo>>


}