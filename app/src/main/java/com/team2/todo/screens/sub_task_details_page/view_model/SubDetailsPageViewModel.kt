package com.team2.todo.screens.sub_task_details_page.view_model

import androidx.lifecycle.ViewModel
import com.team2.todo.data.entities.SubTodo
import com.team2.todo.data.repo.SubTodoRepo
import kotlinx.coroutines.flow.Flow

class SubDetailsPageViewModel(private val repo: SubTodoRepo, subTodoId: Long) : ViewModel() {

    val subTodo = getSubTodosListBasedOnSubTodoId(subTodoId)
    fun getSubTodosListBasedOnSubTodoId(id: Long): Flow<SubTodo> {
        return repo.getSubTodosBasedOnSubTodoIdOrderedByPriority(id)
    }
}