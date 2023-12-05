package com.team2.todo.screens.add_todo.view_model

import androidx.lifecycle.ViewModel
import com.team2.todo.data.entities.SubTodo
import com.team2.todo.data.entities.relations.TodoWithSubTodos
import com.team2.todo.data.repo.SubTodoRepo
import com.team2.todo.data.repo.TodoRepo
import kotlinx.coroutines.flow.Flow

class FetchSubtodoViewModel(val repository: SubTodoRepo): ViewModel() {
    suspend fun fetchSubtodo(todoid:Long): Flow<List<SubTodo>> {
        return repository.getSubTodosBasedOnTodo(todoid)
    }
}