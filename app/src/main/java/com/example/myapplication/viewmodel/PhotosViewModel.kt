package com.example.myapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.model.photos.Photos
import com.example.myapplication.repository.PhotosRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PhotosViewModel : ViewModel() {

    private val repository = PhotosRepository()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _photos = MutableStateFlow<List<Photos>>(emptyList())
    val photos: StateFlow<List<Photos>> = _photos

    init {
        fetchAlbums()
    }

    fun fetchAlbums() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val result = repository.getPhotos() // your API call
                _photos.value = result
            } catch (e: Exception) {
                // handle error if needed
            } finally {
                _isLoading.value = false
            }
        }
    }
}