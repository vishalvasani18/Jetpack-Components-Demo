package com.example.myapplication.repository

import com.example.myapplication.api.RetrofitInstance
import com.example.myapplication.model.users.User

class UsersRepository {

    suspend fun getUsers(): List<User> {
        return RetrofitInstance.api.getUser()
    }
}