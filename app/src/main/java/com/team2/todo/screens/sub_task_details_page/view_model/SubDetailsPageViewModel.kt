package com.team2.todo.screens.sub_task_details_page.view_model

import androidx.lifecycle.ViewModel
import com.team2.todo.data.entities.SubTodo
import com.team2.todo.data.entities.Todo
import com.team2.todo.data.repo.SubTodoRepo
import com.team2.todo.data.repo.TodoRepo
import kotlinx.coroutines.flow.Flow

class SubDetailsPageViewModel(private val repo: SubTodoRepo) : ViewModel() {
    fun getSubTodosListBasedOnSubTodoId(id: Int): Flow<SubTodo> {
        return repo.getSubTodoBasedOnSubTodoId(id)
    }
}