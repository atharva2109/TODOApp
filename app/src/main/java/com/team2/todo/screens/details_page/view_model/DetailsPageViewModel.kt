package com.team2.todo.screens.details_page.view_model

import androidx.lifecycle.ViewModel
import com.team2.todo.data.entities.SubTodo
import com.team2.todo.data.entities.Todo
import com.team2.todo.data.entities.relations.TodoWithSubTodos
import com.team2.todo.data.repo.SubTodoRepo
import com.team2.todo.data.repo.TodoRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

class DetailsPageViewModel(private val repo: TodoRepo, private val subTodoRepo: SubTodoRepo) : ViewModel() {
    var todoListBasedOnId: Flow<List<TodoWithSubTodos>> = emptyFlow()

    var todosList = repo.getAllTodosWithSubTodos();

    fun getTasksBasedOnId(id: Int): Unit{
        todoListBasedOnId = repo.getTodoWithSubTodosBasedOnTodoId(id)
    }

    fun getSubTodosListBasedOnSubTodoId(subTodoId: Int): Flow<SubTodo> {
        return subTodoRepo.getSubTodoBasedOnSubTodoId(subTodoId)
    }



}