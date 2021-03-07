package com.foodie.api.domain.usecase.room

import com.foodie.api.domain.model.room.CreateRoomRequest
import com.foodie.api.domain.model.room.Room
import com.foodie.api.domain.repository.RoomRepository
import domain.usecase.base.BaseUseCase

class CreateRoomUseCase(
    private val roomRepository: RoomRepository
) : BaseUseCase<CreateRoomRequest, Room>() {

    override suspend fun execute(params: CreateRoomRequest): Room {
        return roomRepository.createRoom(params)
    }
}