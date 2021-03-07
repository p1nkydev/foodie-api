package com.foodie.api.data.repository.room

import com.foodie.api.data.datasource.dao.room.RoomDao
import com.foodie.api.domain.model.room.CreateRoomRequest
import com.foodie.api.domain.model.room.Room
import com.foodie.api.domain.model.user.User
import com.foodie.api.domain.repository.RoomRepository

class RoomRepositoryImpl(private val roomDao: RoomDao) : RoomRepository {

    override suspend fun createRoom(createRoomRequest: CreateRoomRequest): Room {
        return roomDao.createRoom(createRoomRequest)
    }

    override suspend fun getAllRoomsForUser(user: User): List<Room> {
        return roomDao.getRoomsForUser(user)
    }

    override suspend fun deleteRoom(room: Room) {
        roomDao.deleteRoom(room)
    }

    override suspend fun getRoomById(id: Long): Room {
        return roomDao.getRoomById(id)
    }

    override suspend fun addFriendToRoom(friendId: Long, roomId: Long) {
        roomDao.addRoomParticipant(friendId, roomId)
    }
}