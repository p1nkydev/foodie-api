package com.foodie.api.domain.repository

import com.foodie.api.domain.model.user.Friend
import com.foodie.api.domain.model.user.Friendship
import com.foodie.api.domain.model.user.User

interface FriendRepository {
    suspend fun createFriendship(user: User, friend: User): Friendship
    suspend fun getFriendsForUser(user: User): List<Friend>
    suspend fun removeFriendShip(user: User, friend: User)
}