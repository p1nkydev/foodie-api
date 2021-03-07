package com.foodie.api.data.datasource.dao.room

import com.foodie.api.data.database.table.RoomParticipants
import com.foodie.api.data.database.table.Rooms
import com.foodie.api.data.mapper.toRoom
import com.foodie.api.domain.model.room.CreateRoomRequest
import com.foodie.api.domain.model.room.Room
import com.foodie.api.domain.model.user.User
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

class RoomDao {


    suspend fun createRoom(roomRequest: CreateRoomRequest): Room = newSuspendedTransaction {
        val id = Rooms.insert {
            it[name] = roomRequest.name
            it[ownerId] = roomRequest.ownerId
        }[Rooms.id]
        Room(id, roomRequest.name)
    }

    suspend fun getRoomsForUser(user: User): List<Room> = newSuspendedTransaction {
        Rooms.select { Rooms.ownerId eq user.id }.map {
            it.toRoom()
        }
    }

    suspend fun deleteRoom(room: Room) {
        newSuspendedTransaction {
            Rooms.deleteWhere { Rooms.id eq room.id }
        }
    }

    suspend fun addRoomParticipant(friendId: Long, idRoom: Long) {
        newSuspendedTransaction {
            RoomParticipants.insert {
                it[roomId] = idRoom
                it[participantId] = friendId
            }
        }
    }

    suspend fun getRoomById(id: Long): Room = newSuspendedTransaction {
        Rooms.select { Rooms.id eq id }
            .map { it.toRoom() }
            .first()
    }

}
