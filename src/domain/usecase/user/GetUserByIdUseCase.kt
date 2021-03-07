package com.foodie.api.domain.usecase.user

import com.foodie.api.domain.model.user.User
import com.foodie.api.domain.repository.UserRepository
import domain.usecase.base.BaseUseCase

class GetUserByIdUseCase(
    private val userRepository: UserRepository
) : BaseUseCase<Long, User>() {

    override suspend fun execute(params: Long): User {
        return userRepository.getUserById(params)
    }
}