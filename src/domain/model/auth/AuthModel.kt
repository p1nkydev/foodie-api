package domain.model.auth

import kotlinx.serialization.Serializable

@Serializable
data class AuthModel(
    val name: String,
    val password: String
)