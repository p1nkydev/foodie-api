package com.foodie.api.data.database.table

import org.jetbrains.exposed.sql.Table

object Friends : Table() {
    val userId = long("userId")
    val friendId = long("friendId")

    override val primaryKey = PrimaryKey(userId, friendId)
}