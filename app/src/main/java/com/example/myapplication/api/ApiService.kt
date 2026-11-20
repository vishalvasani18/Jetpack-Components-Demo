package com.example.myapplication.api

import com.example.myapplication.model.albums.Albums
import com.example.myapplication.model.comments.Comments
import com.example.myapplication.model.photos.Photos
import com.example.myapplication.model.post.Post
import com.example.myapplication.model.todos.TodoItem
import com.example.myapplication.model.users.User
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("posts")
    suspend fun getPosts(
        @Query("_page") page: Int,
        @Query("_limit") limit: Int
    ): List<Post>

    @GET("comments")
    suspend fun getComments(): List<Comments>

    @GET("albums")
    suspend fun getAlbums(): List<Albums>

    @GET("photos")
    suspend fun getPhotos(): List<Photos>

    @GET("todos")
    suspend fun getTodos(): List<TodoItem>

    @GET("users")
    suspend fun getUser(): List<User>
}