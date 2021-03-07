package com.foodie.api.routing.room

import com.foodie.api.domain.model.room.AddFriendToRoomRequest
import com.foodie.api.domain.model.room.CreateRoomRequest
import com.foodie.api.domain.usecase.room.AddFriendToRoomUseCase
import com.foodie.api.domain.usecase.room.CreateRoomUseCase
import com.foodie.api.domain.usecase.room.DeleteRoomByIdUseCase
import com.foodie.api.domain.usecase.room.GetUserRoomsUseCase
import com.foodie.api.routing.session
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.koin.ktor.ext.inject

fun Route.roomApi() {
    val createRoom by inject<CreateRoomUseCase>()
    val deleteRoom by inject<DeleteRoomByIdUseCase>()
    val getUserRooms by inject<GetUserRoomsUseCase>()
    val addFriendToRoom by inject<AddFriendToRoomUseCase>()

    get("api/v1/room/all") {
        call.respond(getUserRooms.execute(call.session.user))
    }

    post("api/v1/room/add_friend") {
        val request = call.receive<AddFriendToRoomRequest>()
        addFriendToRoom.execute(request)
        call.respond(HttpStatusCode.OK)
    }

    post("api/v1/room/create") {
        val createRequest = call.receive<CreateRoomRequest>()
        call.respond(createRoom.execute(createRequest))
    }

    delete("api/v1/room/delete") {
        val roomId = call.parameters["roomId"]?.toLongOrNull() ?: 0
        deleteRoom.execute(roomId)
        call.respond(HttpStatusCode.OK)
    }
}