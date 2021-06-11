package com.foodie.api.routing.coin.model

import kotlinx.serialization.Serializable

@Serializable
data class JoinToRoomRequest(
    val player: Player,
    val room: Room,
)
@Serializable
data class CreateRoomRequest(
    val player: Player,
    val moneyAmount: Int,
)