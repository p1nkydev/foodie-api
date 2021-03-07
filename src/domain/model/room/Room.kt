package com.foodie.api.domain.model.room

import kotlinx.serialization.Serializable

@Serializable
data class Room(val id: Long, val name: String)