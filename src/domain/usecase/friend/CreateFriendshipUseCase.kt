package com.foodie.api.domain.usecase.friend

import com.foodie.api.domain.model.user.Friendship
import com.foodie.api.domain.model.user.User
import com.foodie.api.domain.repository.FriendRepository
import domain.usecase.base.BaseUseCase

class CreateFriendshipUseCase(
    private val friendRepository: FriendRepository
) : BaseUseCase<Pair<User, User>, Friendship>() {

    override suspend fun execute(params: Pair<User, User>): Friendship {
        return friendRepository.createFriendship(params.first, params.second)
    }
}