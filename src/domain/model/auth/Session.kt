package domain.model.auth

import kotlin.random.Random.Default.nextInt

class Session(val user: RegisterModel) {
    val token = nextInt().toString() + user.hashCode().toString()
}