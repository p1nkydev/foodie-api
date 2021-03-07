package com.foodie.api.domain.usecase.room

import com.foodie.api.domain.model.room.Room
import com.foodie.api.domain.model.user.User
import com.foodie.api.domain.repository.RoomRepository
import domain.usecase.base.BaseUseCase

class GetUserRoomsUseCase(private val repository: RoomRepository) : BaseUseCase<User, List<Room>>() {

    override suspend fun execute(params: User): List<Room> {
        return repository.getAllRoomsForUser(params)
    }

}