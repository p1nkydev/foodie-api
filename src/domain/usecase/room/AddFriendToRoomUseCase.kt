package com.foodie.api.domain.usecase.room

import com.foodie.api.domain.model.room.AddFriendToRoomRequest
import com.foodie.api.domain.repository.RoomRepository
import domain.usecase.base.BaseUseCase

class AddFriendToRoomUseCase(
    private val repository: RoomRepository
) : BaseUseCase<AddFriendToRoomRequest, Unit>() {

    override suspend fun execute(params: AddFriendToRoomRequest) {
        repository.addFriendToRoom(params.friendId, params.roomId)
    }

}