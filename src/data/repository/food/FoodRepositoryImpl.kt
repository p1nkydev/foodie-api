package com.foodie.api.data.repository.food

import com.foodie.api.data.datasource.dao.FoodDao
import com.foodie.api.domain.model.food.Food
import com.foodie.api.domain.model.food.FoodCreating
import com.foodie.api.domain.model.food.FoodSharing
import com.foodie.api.domain.model.user.User
import com.foodie.api.domain.repository.FoodRepository

class FoodRepositoryImpl(private val foodDao: FoodDao) : FoodRepository {

    override suspend fun getExistingFoodForUser(user: User): List<Food> {
        return foodDao.getAvailableFoodFor(user)
    }

    override suspend fun shareFood(foodSharing: FoodSharing): FoodSharing {
        return foodDao.shareFood(foodSharing)
    }

    override suspend fun addNewFood(food: FoodCreating) {
        foodDao.addNewFood(food)
    }

    override suspend fun findFoodByName(name: String): List<Food> {
        return foodDao.findFoodByName(name)
    }
}