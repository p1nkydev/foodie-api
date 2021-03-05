package com.foodie.api

import auth.authApi
import domain.model.User
import domain.model.auth.Session
import features.installAll
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.routing.*

val userSessions = mutableListOf<Session>()
val registeredUsers = mutableSetOf<User>()


fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    installAll()

    routing {
        authApi()
        authenticate("core_auth") {
            get("api/v1/food/recommended") {

            }
        }
    }
}

