package com.example.myapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.model.todos.TodoItem
import com.example.myapplication.repository.TodoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TodoViewModel : ViewModel() {

    private val repository = TodoRepository()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _todo = MutableStateFlow<List<TodoItem>>(emptyList())
    val todos: StateFlow<List<TodoItem>> = _todo

    init {
        fetchTodos()
    }

    fun fetchTodos() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val result = repository.getTodo() // your API call
                _todo.value = result
            } catch (e: Exception) {
                // handle error if needed
            } finally {
                _isLoading.value = false
            }
        }
    }
}