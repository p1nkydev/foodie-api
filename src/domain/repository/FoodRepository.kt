package com.foodie.api.domain.repository

import com.foodie.api.domain.model.food.Food
import com.foodie.api.domain.model.food.FoodCreating
import com.foodie.api.domain.model.food.FoodSharing
import com.foodie.api.domain.model.user.User

interface FoodRepository {
    suspend fun getExistingFoodForUser(user: User): List<Food>
    suspend fun shareFood(foodSharing: FoodSharing): FoodSharing
    suspend fun addNewFood(food: FoodCreating)
    suspend fun findFoodByName(name: String): List<Food>
}