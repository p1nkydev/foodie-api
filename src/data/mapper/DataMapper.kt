package com.foodie.api.data.mapper

import com.foodie.api.data.database.table.Foods
import com.foodie.api.data.database.table.Rooms
import com.foodie.api.data.database.table.Users
import com.foodie.api.domain.model.food.Food
import com.foodie.api.domain.model.room.Room
import com.foodie.api.domain.model.user.Friend
import com.foodie.api.domain.model.user.User
import org.jetbrains.exposed.sql.ResultRow

fun ResultRow.toFriend() = Friend(
    id = this[Users.id],
    name = this[Users.name]
)

fun ResultRow.toFood() = Food(
    id = this[Foods.id],
    name = this[Foods.name],
)

fun ResultRow.toUser() = User(
    id = this[Users.id],
    name = this[Users.name],
    password = this[Users.password]
)

fun ResultRow.toRoom() = Room(this[Rooms.id], this[Rooms.name])