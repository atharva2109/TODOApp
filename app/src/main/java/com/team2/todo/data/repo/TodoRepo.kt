package com.team2.todo.data.repo

import com.team2.todo.data.RealEstateDatabase
import com.team2.todo.data.entities.Images
import com.team2.todo.data.entities.Todo
import com.team2.todo.data.entities.relations.TodoWithSubTodos
import kotlinx.coroutines.flow.Flow

class TodoRepo(private val database: RealEstateDatabase) {

    suspend fun upsertTodo(todoEntity: Todo) : Long {
        return database.todoDao().upsertTodo(todoEntity)
    }

    suspend fun addImage(imageEntity: Images) {
        database.todoDao().insertImage(imageEntity)
    }


    fun getTodoWithSubTodosBasedOnTodoId(todoId: Long): Flow<List<TodoWithSubTodos>> =
        database.todoDao().getTodoWithSubTodosBasedOnTodoId(todoId)


    fun getAllTodosWithSubTodos(): Flow<List<TodoWithSubTodos>> =
        database.todoDao().getAllTodosWithSubTodos()


    fun getAllTodosOrderedByPriorityASCWithSubTodos(): Flow<List<TodoWithSubTodos>> =
        database.todoDao().getAllTodosOrderedByPriorityASCWithSubTodos()


    fun getAllTodosOrderedByPriorityDESCWithSubTodos(): Flow<List<TodoWithSubTodos>> =
        database.todoDao().getAllTodosOrderedByPriorityDESCWithSubTodos()


    suspend fun updateTodoStatus(todoId: Long, status: Boolean) {
        database.todoDao().updateTodoStatus(todoId, status)
    }

    fun getAllTodoImagesBasedOnTodo(todoId: Long): Flow<List<Images>> =
        database.todoDao().getAllTodoImagesBasedOnTodo(todoId)

    fun getAllTodosOrderedByPriceASCWithSubTodos(): Flow<List<TodoWithSubTodos>> =
        database.todoDao().getAllTodosOrderedByPriceASCWithSubTodos()

    fun getAllTodosOrderedByPriceDESCWithSubTodos(): Flow<List<TodoWithSubTodos>> =
        database.todoDao().getAllTodosOrderedByPriceDESCWithSubTodos()

}