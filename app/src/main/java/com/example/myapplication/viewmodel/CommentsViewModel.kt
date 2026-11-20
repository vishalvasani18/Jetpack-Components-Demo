package com.example.myapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.model.comments.Comments
import com.example.myapplication.model.post.Post
import com.example.myapplication.repository.CommentsRepository
import com.example.myapplication.repository.PostRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CommentsViewModel : ViewModel() {

    private val repository = CommentsRepository()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _comments = MutableStateFlow<List<Comments>>(emptyList())
    val comments: StateFlow<List<Comments>> = _comments

    init {
        fetchComments()
    }

    fun fetchComments() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val result = repository.getComments() // your API call
                _comments.value = result
            } catch (e: Exception) {
                // handle error if needed
            } finally {
                _isLoading.value = false
            }
        }
    }
}