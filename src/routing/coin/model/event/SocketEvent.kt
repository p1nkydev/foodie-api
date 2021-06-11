package com.foodie.api.routing.coin.model.event

import com.foodie.api.routing.coin.model.CreateRoomRequest
import com.foodie.api.routing.coin.model.Player
import com.foodie.api.routing.coin.model.Room
import com.foodie.api.routing.coin.model.JoinToRoomRequest
import kotlinx.serialization.Serializable

@Serializable
abstract class SocketEvent {
    abstract val type: String

    companion object {
        const val TYPE_PLAYER_JOINED = "p_j"
        const val TYPE_PLAYER_WON = "p_w"
        const val TYPE_GET_ROOMS = "s_g"
        const val TYPE_CREATE_ROOM = "c_r"
        const val TYPE_JOIN_ROOM = "j_r"
        const val TYPE_AVAILABLE_ROOMS_RESPONSE = "a_r"
    }
}

@Serializable
data class CreateRoomEvent(val createRoomRequest: CreateRoomRequest) : SocketEvent() {
    override val type: String = TYPE_CREATE_ROOM
}

@Serializable
data class JoinToRoomEvent(val joinToRoomRequest: JoinToRoomRequest) : SocketEvent() {
    override val type: String = TYPE_JOIN_ROOM
}

@Serializable
class GetRoomsEvent : SocketEvent() {
    override val type: String = TYPE_GET_ROOMS
}

@Serializable
data class AvailableRoomsEvent(val rooms: List<Room>) : SocketEvent() {
    override val type: String = TYPE_AVAILABLE_ROOMS_RESPONSE
}

@Serializable
data class RoomWinnerEvent(
    val roomId: Long,
    val winner: Player,
    val looser: Player,
    val moneyAmount: Int,
    val spins: Int,
    val time: Long
) : SocketEvent() {
    override val type: String = TYPE_PLAYER_WON
}

@Serializable
class PlayerJoinedEvent(val player: Player) : SocketEvent() {
    override val type: String = TYPE_PLAYER_JOINED
}