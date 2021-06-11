package com.foodie.api.routing.coin

import com.foodie.api.routing.coin.model.UserCredentials
import com.foodie.api.routing.coin.model.UserToken
import com.foodie.api.routing.coin.model.event.*
import com.foodie.api.routing.coin.user.UserCacheImpl
import com.google.gson.Gson
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.http.cio.websocket.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.util.pipeline.*
import io.ktor.websocket.*
import kotlinx.coroutines.flow.*

val serializer = Gson()
val cache = UserCacheImpl()

val roomService = RoomService(cache)

fun Route.gameSocket() {
    webSocket("/pidor") {
        incoming.consumeAsFlow()
            .filterIsInstance<Frame.Text>()
            .map { it.readText() }
            .map(::mapSocketEvent)
            .onEach {
                when (it) {
                    is CreateRoomEvent -> {
                        roomService.createRoom(it.createRoomRequest, this)
                    }
                    is GetRoomsEvent -> {
                        sendEvent(AvailableRoomsEvent(roomService.getAllRooms()))
                    }
                    is JoinToRoomEvent -> {
                        roomService.joinPlayerTo(it.joinToRoomRequest.player, it.joinToRoomRequest.room, this)
                    }
                }
            }
            .collect()
    }
}

private fun mapSocketEvent(event: String): SocketEvent {
    return when {
        event.contains(SocketEvent.TYPE_CREATE_ROOM) -> serializer.fromJson(
            event,
            CreateRoomEvent::class.java
        )
        event.contains(SocketEvent.TYPE_GET_ROOMS) -> serializer.fromJson(
            event,
            GetRoomsEvent::class.java
        )
        event.contains(SocketEvent.TYPE_JOIN_ROOM) -> serializer.fromJson(
            event,
            JoinToRoomEvent::class.java
        )
        else -> error("SOSI HUI PADLA")
    }
}
fun Route.login() {
    get("/login") {
        getCredentials()?.let { credentials ->
            val token = cache.login(credentials) ?: cache.registerUser(credentials)
            call.respond(HttpStatusCode.OK, token)
        } ?: call.respond(HttpStatusCode.BadRequest)
    }
}

fun Route.user() {
    get("/user") {
        val token = call.request.header("Authorization")?.removePrefix("Bearer ") ?: kotlin.run {
            call.respond(HttpStatusCode.Unauthorized)
            return@get
        }

        call.respond(HttpStatusCode.OK, cache.getUserByToken(UserToken(token)))
    }
}

fun Route.rooms() {
    get("/room/cancel") {
        val playerId = call.request.queryParameters["playerId"]?.toInt() ?: kotlin.run {
            call.respond(HttpStatusCode.BadRequest)
            return@get
        }
        roomService.cancelGame(playerId)
        call.respond(HttpStatusCode.OK)
    }
}

private fun PipelineContext<Unit, ApplicationCall>.getCredentials(): UserCredentials? {
    val username = call.request.queryParameters["username"] ?: return null
    val password = call.request.queryParameters["password"] ?: return null
    return UserCredentials(username, password)
}

suspend fun <T> WebSocketSession.sendEvent(what: T) {
    send(serializer.toJson(what))
}