package auth

import com.foodie.api.registeredUsers
import com.foodie.api.userSessions
import domain.model.User
import domain.model.auth.AuthModel
import domain.model.auth.RegisterModel
import domain.model.auth.Session
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*


fun Routing.authApi() {
    post("api/v1/register") {
        val model = call.receive<RegisterModel>()
        registeredUsers.firstOrNull { it.name == model.name }
            ?.let { call.respond(HttpStatusCode.Conflict) }
            ?: run {
                val user = User(model.name, model.password)
                registeredUsers.add(user)
                userSessions.add(Session(model))
                call.respond(HttpStatusCode.OK)
            }
    }
    post("api/v1/authorize") {
        val model = call.receive<AuthModel>()
        registeredUsers
            .firstOrNull { it.name == model.name && it.password == model.password }
            ?.let {
                userSessions.add(Session(RegisterModel(it.name, it.password)))
                call.respond(HttpStatusCode.OK)
            }
            ?: kotlin.run {
                call.respond(HttpStatusCode.Forbidden)
                return@post
            }
    }
}