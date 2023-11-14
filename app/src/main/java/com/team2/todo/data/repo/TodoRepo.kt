package com.team2.todo.data.repo

import com.team2.todo.data.RealEstateDatabase
import com.team2.todo.data.entities.Images
import com.team2.todo.data.entities.Todo
import com.team2.todo.data.entities.relations.TodoWithSubTodos
import kotlinx.coroutines.flow.Flow

class TodoRepo(private val database: RealEstateDatabase) {

    suspend fun upsertTodo(todoEntity: Todo) {
        database.todoDao().upsertTodo(todoEntity)
    }


    suspend fun addImage(imageEntity: Images) {
        database.todoDao().insertImage(imageEntity)
    }


    fun getTodoWithSubTodosBasedOnTodoId(todoId: Int): Flow<List<TodoWithSubTodos>> =
        database.todoDao().getTodoWithSubTodosBasedOnTodoId(todoId)


    fun getAllTodosWithSubTodos(): Flow<List<TodoWithSubTodos>> =
        database.todoDao().getAllTodosWithSubTodos()


    fun getAllTodosOrderedByPriorityWithSubTodos(): Flow<List<TodoWithSubTodos>> =
        database.todoDao().getAllTodosOrderedByPriorityWithSubTodos()


    suspend fun updateTodoStatus(todoId: Int, status: Boolean) {
        database.todoDao().updateTodoStatus(todoId, status)
    }

    fun getAllTodoImagesBasedOnTodo(todoId: Int): Flow<List<Images>> =
        database.todoDao().getAllTodoImagesBasedOnTodo(todoId)


}