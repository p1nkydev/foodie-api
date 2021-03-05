package features

import com.foodie.api.userSessions
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.features.*
import io.ktor.response.*
import io.ktor.serialization.*
import kotlinx.serialization.json.Json
import javax.naming.AuthenticationException

fun Application.installAll() {
    install(DefaultHeaders)
    install(CallLogging)
    install(Authentication) {
        basic(name = "core_auth") {
            realm = "Ktor Server"
            validate { creds ->
                userSessions
                    .firstOrNull { creds.name == it.user.name && creds.password == it.user.password }
                    ?.user
            }
        }
    }
    install(StatusPages) {
        exception<Throwable> { call.respond(io.ktor.http.HttpStatusCode.InternalServerError) }
        exception<AuthenticationException> {
            call.respond(io.ktor.http.HttpStatusCode.Unauthorized)
        }
    }
    install(ContentNegotiation) {
        json(Json { prettyPrint = true; isLenient = true })
    }
}