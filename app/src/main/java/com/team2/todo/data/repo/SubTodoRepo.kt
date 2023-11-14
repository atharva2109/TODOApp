package com.team2.todo.data.repo

import com.team2.todo.data.RealEstateDatabase
import com.team2.todo.data.entities.SubTodo
import kotlinx.coroutines.flow.Flow

class SubTodoRepo(private val database: RealEstateDatabase) {

    suspend fun addSubTodo(subTodoEntity: SubTodo) {
        database.subTodoDao().upsertSubTodo(subTodoEntity)
    }

    fun getSubTodoBasedOnTodo(todoId: Int): Flow<List<SubTodo>> =
        database.subTodoDao().getSubTodoBasedOnTodo(todoId)

    fun getSubTodoBasedOnSubTodoId(subTodoId: Int): Flow<SubTodo> =
        database.subTodoDao().getSubTodoBasedOnSubTodoId(subTodoId)

    fun getSubTodosBasedOnSubTodoIdOrderedByPriority(subTodoId: Int): Flow<SubTodo> =
        database.subTodoDao().getSubTodosBasedOnSubTodoIdOrderedByPriority(subTodoId)


    fun getSubTodoOrderedByPriority(todoId: Int): Flow<List<SubTodo>> =
        database.subTodoDao().getSubTodoOrderedByPriority(todoId)

}