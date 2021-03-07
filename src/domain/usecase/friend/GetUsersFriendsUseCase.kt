package com.foodie.api.domain.usecase.friend

import com.foodie.api.domain.model.user.Friend
import com.foodie.api.domain.model.user.User
import com.foodie.api.domain.repository.FriendRepository
import domain.usecase.base.BaseUseCase

class GetUsersFriendsUseCase(private val friendRepository: FriendRepository) : BaseUseCase<User, List<Friend>>() {

    override suspend fun execute(params: User): List<Friend> {
        return friendRepository.getFriendsForUser(params)
    }
}