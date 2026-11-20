package com.example.myapplication.repository

import com.example.myapplication.api.RetrofitInstance
import com.example.myapplication.model.comments.Comments

class CommentsRepository {

    suspend fun getComments(): List<Comments> {
        return RetrofitInstance.api.getComments()
    }
}