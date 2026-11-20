package com.example.myapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.model.comments.Comments
import com.example.myapplication.model.users.User
import com.example.myapplication.repository.CommentsRepository
import com.example.myapplication.repository.UsersRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {

    private val repository = UsersRepository()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _users = MutableStateFlow<List<User>>(emptyList())
    val users: StateFlow<List<User>> = _users

    init {
        fetchComments()
    }

    fun fetchComments() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val result = repository.getUsers() // your API call
                _users.value = result
            } catch (e: Exception) {
                // handle error if needed
            } finally {
                _isLoading.value = false
            }
        }
    }
}