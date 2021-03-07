package com.foodie.api

import com.foodie.api.data.database.Database
import com.foodie.api.routing.food.foodApi
import com.foodie.api.routing.friend.friendsApi
import com.foodie.api.routing.room.roomApi
import features.installAllFeatures
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.routing.*
import routing.auth.authApi

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    Database.init()
    installAllFeatures()

    routing {
        authApi()
        authenticate {
            friendsApi()
            roomApi()
            foodApi()
        }
    }
}
