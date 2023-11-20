package com.team2.todo.data.repo

import com.team2.todo.data.RealEstateDatabase
import com.team2.todo.data.entities.Images
import com.team2.todo.data.entities.Todo
import com.team2.todo.data.entities.relations.TodoWithSubTodos
import kotlinx.coroutines.flow.Flow

class TodoRepo(private val database: RealEstateDatabase) {

    suspend fun addTodo(todoEntity: Todo) {
        database.todoDao().upsertTodo(todoEntity)
    }

    fun getTodoWithSubTodosBasedOnTodoId(todoId: Int): Flow<List<TodoWithSubTodos>> =
        database.todoDao().getTodoWithSubTodosBasedOnTodoId(todoId)


    fun getAllTodosWithSubTodos(): Flow<List<TodoWithSubTodos>> =
        database.todoDao().getAllTodosWithSubTodos()


    fun getAllTodosOrderedByPriorityWithSubTodos(): Flow<List<TodoWithSubTodos>> =
        database.todoDao().getAllTodosOrderedByPriorityWithSubTodos()

    fun getAllTodoImagesBasedOnTodo(todoId: Long): Flow<List<Images>> =
        database.todoDao().getAllTodoImagesBasedOnTodo(todoId)


}