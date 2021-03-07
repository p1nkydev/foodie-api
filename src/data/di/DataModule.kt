package com.foodie.api.data.di

import com.foodie.api.data.datasource.SessionManager
import com.foodie.api.data.datasource.UserSessionManager
import com.foodie.api.data.datasource.dao.FoodDao
import com.foodie.api.data.datasource.dao.FriendsDao
import com.foodie.api.data.datasource.dao.UserDao
import com.foodie.api.data.datasource.dao.room.RoomDao
import com.foodie.api.data.repository.auth.AuthRepositoryImpl
import com.foodie.api.data.repository.food.FoodRepositoryImpl
import com.foodie.api.data.repository.friend.FriendRepositoryImpl
import com.foodie.api.data.repository.room.RoomRepositoryImpl
import com.foodie.api.data.repository.user.UserRepositoryImpl
import com.foodie.api.domain.repository.FoodRepository
import com.foodie.api.domain.repository.FriendRepository
import com.foodie.api.domain.repository.RoomRepository
import com.foodie.api.domain.repository.UserRepository
import domain.repository.AuthRepository
import org.koin.dsl.module

val dataModule = module {

    //repository
    factory<AuthRepository> {
        AuthRepositoryImpl(
            sessionManager = get(),
            userDao = get()
        )
    }

    factory<FriendRepository> { FriendRepositoryImpl(friendsDao = get()) }
    factory<UserRepository> { UserRepositoryImpl(userDao = get()) }
    factory<FoodRepository> { FoodRepositoryImpl(foodDao = get()) }
    factory<RoomRepository> { RoomRepositoryImpl(roomDao = get()) }




    //data source

    // User
    single<SessionManager>(createdAtStart = true) { UserSessionManager() }
    factory { UserDao() }
    factory { FriendsDao() }


    // Food
    factory { FoodDao() }

    // Rooms
    factory { RoomDao() }
}