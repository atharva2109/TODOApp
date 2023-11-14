package com.team2.todo.screens.sub_task_details_page.view_model

import androidx.lifecycle.ViewModel
import com.team2.todo.data.entities.SubTodo
import com.team2.todo.data.entities.Todo
import com.team2.todo.data.repo.SubTodoRepo
import com.team2.todo.data.repo.TodoRepo
import kotlinx.coroutines.flow.Flow

class SubDetailsPageViewModel(private val repo: SubTodoRepo, subTodoId: Int) : ViewModel() {

    val subTodo = getSubTodosListBasedOnSubTodoId(subTodoId)
    fun getSubTodosListBasedOnSubTodoId(id: Int): Flow<SubTodo> {
        return repo.getSubTodosBasedOnSubTodoIdOrderedByPriority(id)
    }
}