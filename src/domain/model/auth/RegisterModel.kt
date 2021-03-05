package domain.model.auth

import io.ktor.auth.*
import kotlinx.serialization.Serializable

@Serializable
class RegisterModel(
    val name: String,
    val password: String
) : Principal