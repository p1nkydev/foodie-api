package com.foodie.api.data.datasource.dao

import com.foodie.api.data.database.table.Users
import com.foodie.api.data.mapper.toUser
import com.foodie.api.domain.model.user.User
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

class UserDao {

    fun getUsersByName(name: String): List<User> = transaction {
        Users.select { Users.name eq name }
            .map { User(id = it[Users.id], name = it[Users.name], password = it[Users.password]) }
    }

    fun insertUser(name: String, password: String): User {
        val id = transaction {
            Users.insert {
                it[Users.name] = name
                it[Users.password] = password
            }
        }[Users.id]
        return User(id = id, name = name, password = password)
    }

    fun getUserById(id: Long): User = transaction {
        Users
            .select { Users.id eq id }
            .map { it.toUser() }
            .first()
    }

}
