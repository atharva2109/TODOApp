package com.team2.todo.data.repo

import com.team2.todo.data.RealEstateDatabase
import com.team2.todo.data.entities.Todo

class TodoRepo(private val database : RealEstateDatabase ) {

    suspend fun addTodo(todoEntity : Todo)
    {
        database.todoDao().upsertTodo(todoEntity)
    }
    fun getTodoWithSubTodosBasedOnTodoId(todoId : Int)
    {
     database.todoDao().getTodoWithSubTodosBasedOnTodoId(todoId)
    }
    fun getAllTodosWithSubTodos()
    {
        database.todoDao().getAllTodosWithSubTodos()
    }
    fun getAllTodosOrderedByPriorityWithSubTodos()
    {
        database.todoDao().getAllTodosOrderedByPriorityWithSubTodos()
    }
}