package com.foodie.api.domain.usecase.friend

import com.foodie.api.domain.model.user.User
import com.foodie.api.domain.repository.FriendRepository
import domain.usecase.base.BaseUseCase

class RemoveFriendshipUseCase(
    private val friendRepository: FriendRepository
) : BaseUseCase<Pair<User, User>, Unit>() {

    override suspend fun execute(params: Pair<User, User>) {
        friendRepository.removeFriendShip(params.first, params.second)
    }
}