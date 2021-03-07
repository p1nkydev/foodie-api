package com.foodie.api.routing.friend

import com.foodie.api.domain.model.user.Friend
import com.foodie.api.domain.usecase.friend.CreateFriendshipUseCase
import com.foodie.api.domain.usecase.friend.GetUsersFriendsUseCase
import com.foodie.api.domain.usecase.friend.RemoveFriendshipUseCase
import com.foodie.api.domain.usecase.user.GetUserByIdUseCase
import com.foodie.api.domain.usecase.user.GetUserByNameUseCase
import com.foodie.api.routing.session
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import org.koin.ktor.ext.inject

fun Route.friendsApi() {
    val getFriendsUseCase by inject<GetUsersFriendsUseCase>()
    val getUserByNameUseCase by inject<GetUserByNameUseCase>()
    val createFriendshipUseCase by inject<CreateFriendshipUseCase>()
    val removeFriendshipUseCase by inject<RemoveFriendshipUseCase>()
    val getUserByIdUseCase by inject<GetUserByIdUseCase>()


    get("api/v1/friend/all") {
        call.respond(getFriendsUseCase.execute(call.session.user))
    }

    get("api/v1/friend/find") {
        val query = call.parameters["query"] ?: throw NotFoundException()
        val friends = getUserByNameUseCase.execute(query).map { Friend(it.id, it.name) }
        call.respond(friends)
    }

    post("api/v1/friend/add") {
        val session = call.session
        val friendId = call.parameters["friendId"]?.toLongOrNull() ?: throw NotFoundException()
        val friend = getUserByIdUseCase.execute(friendId)
        val friendship = createFriendshipUseCase.execute(session.user to friend)
        call.respond(friendship)
    }

    post("api/v1/friend/remove") {
        val session = call.session
        val friendId = call.parameters["friendId"]?.toLongOrNull() ?: throw NotFoundException()
        val friend = getUserByIdUseCase.execute(friendId)
        removeFriendshipUseCase.execute(session.user to friend)
        call.respond(HttpStatusCode.OK)
    }
}