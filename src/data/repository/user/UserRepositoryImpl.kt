package com.foodie.api.data.repository.user

import com.foodie.api.data.datasource.dao.UserDao
import com.foodie.api.domain.model.user.User
import com.foodie.api.domain.repository.UserRepository

class UserRepositoryImpl(
    private val userDao: UserDao,
) : UserRepository {

    override suspend fun getUsersByName(name: String): List<User> {
        return userDao.getUsersByName(name)
    }

    override suspend fun getUserById(id: Long): User {
        return userDao.getUserById(id)
    }
}