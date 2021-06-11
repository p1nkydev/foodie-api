package domain.model.auth

import com.foodie.api.domain.model.auth.Token
import com.foodie.api.domain.model.user.User
import io.ktor.auth.*
import java.util.*

class Session(val user: User) : Principal {
    val token = Token(
        Base64
            .getEncoder()
            .withoutPadding()
            .encode("${user.name}:${user.password}".toByteArray()).toString(Charsets.UTF_8)
    )
}