package domain.repository

import domain.model.auth.RegisterModel

interface AuthRepository {
    suspend fun registerUser(registerModel: RegisterModel)
}