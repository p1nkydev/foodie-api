package com.foodie.api.domain.model.room

import kotlinx.serialization.Serializable

@Serializable
class AddFriendToRoomRequest(val friendId: Long, val roomId: Long)