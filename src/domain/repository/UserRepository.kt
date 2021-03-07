package com.foodie.api.domain.repository

import com.foodie.api.domain.model.user.User

interface UserRepository {
    suspend fun getUsersByName(name: String): List<User>
    suspend fun getUserById(id: Long): User
}