package com.example.myapplication.api

import com.example.myapplication.model.post.Post
import retrofit2.http.GET

interface ApiService {
    @GET("posts")
    suspend fun getPosts(): List<Post>
}