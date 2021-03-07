package com.foodie.api.domain.usecase.user

import com.foodie.api.domain.model.user.User
import com.foodie.api.domain.repository.UserRepository
import domain.usecase.base.BaseUseCase

class GetUserByNameUseCase(
    private val userRepository: UserRepository,
) : BaseUseCase<String, List<User>>() {

    override suspend fun execute(params: String): List<User> {
        return userRepository.getUsersByName(params)
    }
}