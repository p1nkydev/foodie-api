package auth

import com.foodie.api.registeredUsers
import com.foodie.api.userSessions
import domain.model.User
import domain.model.auth.AuthModel
import domain.model.auth.Session
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*


fun Routing.authApi() {
    post("api/v1/authorize") {
        val model = call.receiveOrNull<AuthModel>() ?: AuthModel("hui","zal")

        val registered = registeredUsers.firstOrNull { it.name == model.name }
        when {
            registered == null -> {
                val user = User(model.name, model.password)
                val session = Session(user)
                registeredUsers.add(user)
                userSessions.add(session)
                call.respond(HttpStatusCode.OK, session.token)
            }
            registered.password == model.password -> {
                val session = Session(User(model.name, model.password))
                userSessions.add(session)
                call.respond(HttpStatusCode.OK, session.token)
            }
            else -> call.respond(HttpStatusCode.Forbidden)
        }
    }
}