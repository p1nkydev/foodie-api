package com.foodie.api.data.repository.auth

import com.foodie.api.data.datasource.SessionManager
import com.foodie.api.data.datasource.dao.UserDao
import com.foodie.api.domain.model.user.User
import domain.model.auth.AuthModel
import domain.model.auth.Session
import domain.repository.AuthRepository

class AuthRepositoryImpl(
    private val sessionManager: SessionManager,
    private val userDao: UserDao
) : AuthRepository {

    override suspend fun registerUser(authModel: AuthModel): User {
        return userDao.insertUser(authModel.name, authModel.password)
    }

    override suspend fun createSession(user: User): Session {
        return Session(user).also { sessionManager.addSession(it) }
    }
}