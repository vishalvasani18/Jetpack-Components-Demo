package com.example.myapplication.repository

import com.example.myapplication.api.RetrofitInstance
import com.example.myapplication.model.photos.Photos

class PhotosRepository {

    suspend fun getPhotos(): List<Photos> {
        return RetrofitInstance.api.getPhotos()
    }
}