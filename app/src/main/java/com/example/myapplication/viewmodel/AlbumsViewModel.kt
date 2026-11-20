package com.example.myapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.model.albums.Albums
import com.example.myapplication.repository.AlbumsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AlbumsViewModel : ViewModel() {

    private val repository = AlbumsRepository()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _albums = MutableStateFlow<List<Albums>>(emptyList())
    val albums: StateFlow<List<Albums>> = _albums

    init {
        fetchAlbums()
    }

    fun fetchAlbums() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val result = repository.getAlbums() // your API call
                _albums.value = result
            } catch (e: Exception) {
                // handle error if needed
            } finally {
                _isLoading.value = false
            }
        }
    }
}