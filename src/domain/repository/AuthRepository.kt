package domain.repository

import com.foodie.api.domain.model.user.User
import domain.model.auth.AuthModel
import domain.model.auth.Session

interface AuthRepository {
    suspend fun registerUser(authModel: AuthModel): User
    suspend fun createSession(user: User): Session
}