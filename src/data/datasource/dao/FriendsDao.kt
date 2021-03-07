package com.foodie.api.data.datasource.dao

import com.foodie.api.data.database.table.Friends
import com.foodie.api.data.database.table.Users
import com.foodie.api.domain.model.user.Friend
import com.foodie.api.domain.model.user.Friendship
import com.foodie.api.domain.model.user.User
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

class FriendsDao {

    suspend fun createFriendship(user: User, friend: User): Friendship {
        val friend1 = Friend(user.id, user.name)
        val friend2 = Friend(friend.id, friend.name)
        val friendship = Friendship(friend1, friend2)
        newSuspendedTransaction {
            Friends.insert {
                it[userId] = friend1.id
                it[friendId] = friend2.id
            }
        }
        return friendship
    }

    suspend fun findUserFriends(user: User): List<Friend> = newSuspendedTransaction {
        Friends.select { Friends.userId eq user.id }.map { friendship ->
            val id = friendship[Friends.friendId]
            Users
                .select { Users.id eq id }
                .limit(1)
                .map { Friend(it[Users.id], it[Users.name]) }
                .first()
        }
    }

    suspend fun removeFriendShip(user: User, friend: User) {
        newSuspendedTransaction {
            Friends.deleteWhere {
                Friends.userId eq user.id and (Friends.friendId eq friend.id)
            }
        }
    }
}