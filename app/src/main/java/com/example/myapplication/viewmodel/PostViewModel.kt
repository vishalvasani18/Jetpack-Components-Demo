package com.example.myapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.model.post.Post
import com.example.myapplication.repository.PostRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PostViewModel : ViewModel() {

    private val repository = PostRepository()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _isLoadingMore = MutableStateFlow(false)
    val isLoadingMore: StateFlow<Boolean> = _isLoadingMore

    private val _posts = MutableStateFlow<List<Post>>(emptyList())
    val posts: StateFlow<List<Post>> = _posts

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    private var currentPage = 1
    private val pageSize = 4
    private var hasMorePages = true

    init {
        fetchPosts()
    }

    fun fetchPosts() {
        if (_isLoading.value) return

        currentPage = 1
        hasMorePages = true
        _isLoading.value = true
        _error.value = null

        viewModelScope.launch {
            try {
                val result = repository.getPosts(
                    page = currentPage,
                    limit = pageSize
                )

                _posts.value = result
                hasMorePages = result.size == pageSize
            } catch (e: Exception) {
                _error.value = e.message ?: "Failed to load posts"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun loadNextPage() {
        if (_isLoading.value ||
            _isLoadingMore.value ||
            !hasMorePages
        ) return

        _isLoadingMore.value = true
        _error.value = null

        val nextPage = currentPage + 1

        viewModelScope.launch {
            try {
                val result = repository.getPosts(
                    page = nextPage,
                    limit = pageSize
                )

                currentPage = nextPage
                _posts.value = _posts.value + result
                hasMorePages = result.size == pageSize
            } catch (e: Exception) {
                _error.value = e.message ?: "Failed to load more posts"
            } finally {
                _isLoadingMore.value = false
            }
        }
    }
}
