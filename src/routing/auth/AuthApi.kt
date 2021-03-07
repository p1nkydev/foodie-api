package routing.auth

import com.foodie.api.domain.usecase.auth.AuthorizeUseCase
import domain.model.auth.AuthModel
import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.koin.ktor.ext.inject


fun Routing.authApi() {
    val authorizeUseCase by inject<AuthorizeUseCase>()

    post("/api/v1/authorize") {
        val model = call.receive<AuthModel>()
        val token = authorizeUseCase.execute(model).token
        call.respond(token)
    }
}