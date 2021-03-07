package com.foodie.api.data.repository.friend

import com.foodie.api.data.datasource.dao.FriendsDao
import com.foodie.api.domain.model.user.Friend
import com.foodie.api.domain.model.user.Friendship
import com.foodie.api.domain.model.user.User
import com.foodie.api.domain.repository.FriendRepository

class FriendRepositoryImpl(
    private val friendsDao: FriendsDao,
) : FriendRepository {

    override suspend fun createFriendship(user: User, friend: User): Friendship {
        return friendsDao.createFriendship(user, friend)
    }

    override suspend fun getFriendsForUser(user: User): List<Friend> {
        return friendsDao.findUserFriends(user)
    }

    override suspend fun removeFriendShip(user: User, friend: User) {
        friendsDao.removeFriendShip(user, friend)
    }
}