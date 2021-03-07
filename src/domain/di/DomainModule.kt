package com.foodie.api.domain.di

import com.foodie.api.domain.usecase.auth.AuthorizeUseCase
import com.foodie.api.domain.usecase.food.AddNewFoodUseCase
import com.foodie.api.domain.usecase.food.FindFoodByNameUseCase
import com.foodie.api.domain.usecase.food.GetUserExistingFoodUseCase
import com.foodie.api.domain.usecase.food.ShareFoodUseCase
import com.foodie.api.domain.usecase.friend.CreateFriendshipUseCase
import com.foodie.api.domain.usecase.friend.GetUsersFriendsUseCase
import com.foodie.api.domain.usecase.room.AddFriendToRoomUseCase
import com.foodie.api.domain.usecase.room.CreateRoomUseCase
import com.foodie.api.domain.usecase.room.DeleteRoomByIdUseCase
import com.foodie.api.domain.usecase.room.GetUserRoomsUseCase
import com.foodie.api.domain.usecase.user.GetUserByIdUseCase
import com.foodie.api.domain.usecase.user.GetUserByNameUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { AuthorizeUseCase(authRepository = get(), userRepository = get()) }

    factory { GetUsersFriendsUseCase(friendRepository = get()) }
    factory { CreateFriendshipUseCase(friendRepository = get()) }
    factory { GetUserByIdUseCase(userRepository = get()) }
    factory { GetUserByNameUseCase(userRepository = get()) }

    factory { AddNewFoodUseCase(repository = get()) }
    factory { FindFoodByNameUseCase(foodRepository = get()) }
    factory { GetUserExistingFoodUseCase(foodRepository = get()) }
    factory { ShareFoodUseCase(foodRepository = get()) }

    factory { CreateRoomUseCase(roomRepository = get()) }
    factory { DeleteRoomByIdUseCase(repository = get()) }
    factory { GetUserRoomsUseCase(repository = get()) }
    factory { AddFriendToRoomUseCase(repository = get()) }
}