package com.team2.todo.screens.details_page.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.team2.todo.data.entities.SubTodo
import com.team2.todo.data.entities.Todo
import com.team2.todo.data.entities.relations.TodoWithSubTodos
import com.team2.todo.data.repo.SubTodoRepo
import com.team2.todo.data.repo.TodoRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch

class DetailsPageViewModel(private val repo: TodoRepo, private val subTodoRepo: SubTodoRepo) : ViewModel() {


    var todosList = repo.getAllTodosWithSubTodos();

    fun getPropertyFromId(propertyId:Int):Flow<List<TodoWithSubTodos>>{
        return  repo.getTodoWithSubTodosBasedOnTodoId(propertyId)
    }

    fun getSubTodosListBasedOnSubTodoId(subTodoId: Int): Flow<SubTodo> {
        return subTodoRepo.getSubTodoBasedOnSubTodoId(subTodoId)
    }

    fun getSubTodosBasedOnId(subTodoId: Int): Flow<SubTodo>{
        return subTodoRepo.getSubTodosBasedOnSubTodoIdOrderedByPriority(subTodoId)
    }

    fun addTodo(todos: Todo): Unit{
        viewModelScope.launch {
            repo.addTodo(todos)
        }
    }

    fun addSubTodos(subTodo: SubTodo){
        viewModelScope.launch {
            subTodoRepo.addSubTodo(subTodo)
        }
    }



}