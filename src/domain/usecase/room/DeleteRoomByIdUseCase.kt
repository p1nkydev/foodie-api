package com.foodie.api.domain.usecase.room

import com.foodie.api.domain.repository.RoomRepository
import domain.usecase.base.BaseUseCase

class DeleteRoomByIdUseCase(
    private val repository: RoomRepository
) : BaseUseCase<Long, Unit>() {

    override suspend fun execute(params: Long) {
        val room = repository.getRoomById(params)
        repository.deleteRoom(room)
    }
}