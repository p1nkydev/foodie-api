package com.foodie.api.routing.coin.model

import kotlinx.serialization.Serializable

@Serializable
data class Player(
    val id: Int = 0,
    val name: String = "",
    val joinedSide: Int
)