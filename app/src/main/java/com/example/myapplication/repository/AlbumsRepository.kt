package com.example.myapplication.repository

import com.example.myapplication.api.RetrofitInstance
import com.example.myapplication.model.albums.Albums

class AlbumsRepository {

    suspend fun getAlbums(): List<Albums> {
        return RetrofitInstance.api.getAlbums()
    }
}