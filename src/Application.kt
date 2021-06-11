package com.foodie.api

import com.foodie.api.data.database.Database
import com.foodie.api.routing.coin.gameSocket
import com.foodie.api.routing.coin.login
import com.foodie.api.routing.coin.rooms
import com.foodie.api.routing.coin.user
import com.foodie.api.routing.food.foodApi
import com.foodie.api.routing.friend.friendsApi
import com.foodie.api.routing.room.roomApi
import features.installAllFeatures
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.http.cio.websocket.*
import io.ktor.routing.*
import io.ktor.websocket.*
import routing.auth.authApi
import java.time.Duration

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


        // coinflip
        install(WebSockets) {
            pingPeriod = Duration.ofSeconds(1000)
            timeout = Duration.ofSeconds(120)
            maxFrameSize = Long.MAX_VALUE
            masking = false
        }
        login()
        rooms()
        user()
        gameSocket()
    }
}
