package domain.model.auth

import domain.model.User
import io.ktor.auth.*
import kotlin.random.Random.Default.nextInt

class Session(val user: User) : Principal {
    val token = nextInt().toString() + user.hashCode().toString()
}