package features

import com.foodie.api.data.datasource.SessionManager
import com.foodie.api.data.di.dataModule
import com.foodie.api.domain.di.domainModule
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.features.*
import io.ktor.response.*
import io.ktor.serialization.*
import org.koin.ktor.ext.Koin
import org.koin.ktor.ext.get
import javax.naming.AuthenticationException

fun Application.installAllFeatures() {
    install(DefaultHeaders)
    install(CallLogging)
    install(Koin) {
        modules(domainModule + dataModule)
    }
    install(ContentNegotiation) {
        json()
    }
    install(Authentication) {
        basic {
            realm = "Ktor Server"
            validate { creds ->
                get<SessionManager>().getSession(creds.name, creds.password)
            }
        }
    }
    install(StatusPages) {
        exception<Throwable> { call.respond(io.ktor.http.HttpStatusCode.InternalServerError) }
        exception<AuthenticationException> {
            call.respond(io.ktor.http.HttpStatusCode.Unauthorized)
        }
    }
}