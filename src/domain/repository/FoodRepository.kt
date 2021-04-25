package com.foodie.api.domain.repository

import com.foodie.api.domain.model.food.*
import com.foodie.api.domain.model.user.User

interface FoodRepository {
    suspend fun getExistingFoodForUser(user: User): List<AvailableFood>
    suspend fun shareFood(foodSharing: FoodSharing): FoodSharing
    suspend fun addNewFood(food: FoodCreating)
    suspend fun findFoodByName(name: String): List<Food>
}