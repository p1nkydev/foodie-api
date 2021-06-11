package com.foodie.api.routing.coin.user

import com.foodie.api.routing.coin.model.User
import com.foodie.api.routing.coin.model.UserCredentials
import com.foodie.api.routing.coin.model.UserToken

interface UserCache {
    fun registerUser(credentials: UserCredentials): UserToken
    fun login(credentials: UserCredentials): UserToken?
    fun getUserById(id: Int): User
    fun getUserByName(name: String): User?
    fun updateUser(user: User): User
    fun getUserByToken(token: UserToken): User
}