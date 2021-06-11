package com.foodie.api.routing.coin.model

import kotlinx.serialization.Serializable

@Serializable
data class Room(
    val id: Long = 0L,
    val moneyAmount: Int,
    var players: List<Player>,
) {
    override fun hashCode(): Int {
        return super.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Room

        if (id != other.id) return false

        return true
    }
}