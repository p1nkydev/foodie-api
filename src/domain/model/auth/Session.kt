package domain.model.auth

import com.foodie.api.domain.model.user.User
import io.ktor.auth.*
import java.util.*

class Session(val user: User) : Principal {
    val token = Base64
            .getEncoder()
            .withoutPadding()
            .encode("${user.name}:${user.password}".encodeToByteArray())
}