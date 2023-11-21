package com.team2.todo.data.repo

import com.team2.todo.data.RealEstateDatabase
import com.team2.todo.data.entities.SubTodo
import kotlinx.coroutines.flow.Flow

class SubTodoRepo(private val database: RealEstateDatabase) {

    suspend fun upsertSubTodo(subTodoEntity: SubTodo) : Long {
        return database.subTodoDao().upsertSubTodo(subTodoEntity)
    }

    fun getSubTodosBasedOnTodo(todoId: Long): Flow<List<SubTodo>> =
        database.subTodoDao().getSubTodosBasedOnTodo(todoId)

    fun getSubTodosBasedOnSubTodoIdOrderedByPriority(subTodoId: Int): Flow<SubTodo> =
        database.subTodoDao().getSubTodosBasedOnSubTodoIdOrderedByPriority(subTodoId)

    suspend fun updateSubTodoStatus(subTodoId: Long, status: Boolean) {
        database.subTodoDao().updateSubTodoStatus(subTodoId, status)
    }

}