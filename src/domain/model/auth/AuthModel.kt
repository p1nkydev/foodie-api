package domain.model.auth

import kotlinx.serialization.Serializable

@Serializable
class AuthModel(
    val name: String,
    val password: String
)