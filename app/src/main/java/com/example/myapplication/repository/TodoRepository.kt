package com.example.myapplication.repository

import com.example.myapplication.api.RetrofitInstance
import com.example.myapplication.model.todos.TodoItem

class TodoRepository {

    suspend fun getTodo(): List<TodoItem> {
        return RetrofitInstance.api.getTodos()
    }
}