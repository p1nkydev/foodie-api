package com.foodie.api.domain.usecase.auth

import com.foodie.api.domain.repository.UserRepository
import domain.model.auth.AuthModel
import domain.model.auth.Session
import domain.repository.AuthRepository
import domain.usecase.base.BaseUseCase
import io.ktor.features.*

class AuthorizeUseCase(
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository
) : BaseUseCase<AuthModel, Session>() {

    override suspend fun execute(params: AuthModel): Session {
        val user = userRepository.getUsersByName(params.name).firstOrNull()
        return when {
            user == null -> {
                authRepository.registerUser(params).let { authRepository.createSession(it) }
            }
            user.password == params.password -> {
                authRepository.createSession(user)
            }
            else -> throw NotFoundException()
        }
    }
}