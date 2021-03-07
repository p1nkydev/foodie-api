package com.foodie.api.domain.repository

import com.foodie.api.domain.model.room.CreateRoomRequest
import com.foodie.api.domain.model.room.Room
import com.foodie.api.domain.model.user.User

interface RoomRepository {
    suspend fun createRoom(createRoomRequest: CreateRoomRequest): Room
    suspend fun getAllRoomsForUser(user: User): List<Room>
    suspend fun deleteRoom(room: Room)
    suspend fun getRoomById(id: Long): Room
    suspend fun addFriendToRoom(friendId: Long, roomId: Long)
}