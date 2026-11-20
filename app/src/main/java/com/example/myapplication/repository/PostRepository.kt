package com.example.myapplication.repository

import com.example.myapplication.api.RetrofitInstance
import com.example.myapplication.api.RetrofitInstance.api
import com.example.myapplication.model.post.Post

class PostRepository {

    suspend fun getPosts(
        page: Int,
        limit: Int
    ): List<Post> {
        return api.getPosts(page = page, limit = limit)
    }
}