package com.example.myapplication.model.todos

data class TodoItem(
    val completed: Boolean,
    val id: Int,
    val title: String,
    val userId: Int
)