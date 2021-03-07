package com.foodie.api.data.datasource.dao

import com.foodie.api.data.database.table.Foods
import com.foodie.api.data.database.table.RoomFoodProperties
import com.foodie.api.data.database.table.SharedFoods
import com.foodie.api.data.mapper.toFood
import com.foodie.api.domain.model.food.Food
import com.foodie.api.domain.model.food.FoodCreating
import com.foodie.api.domain.model.food.FoodSharing
import com.foodie.api.domain.model.user.User
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

class FoodDao {


    suspend fun getAvailableFoodFor(user: User): List<Food> {
        return newSuspendedTransaction {
            SharedFoods
                .select { SharedFoods.ownerId eq user.id }
                .map { it[SharedFoods.foodId] }
                .map { foodId -> Foods.select { Foods.id eq foodId }.map { it.toFood() } }
                .flatten()
        }
    }

    suspend fun shareFood(foodSharing: FoodSharing): FoodSharing {
        newSuspendedTransaction {
            RoomFoodProperties.insert {
                it[roomId] = foodSharing.roomId
                it[foodPropertyId] = foodSharing.foodPropertyId
            }
        }
        return foodSharing
    }

    suspend fun addNewFood(food: FoodCreating) {
        newSuspendedTransaction {
            Foods.insert {
                it[name] = food.name
            }
        }
    }

    suspend fun findFoodByName(name: String): List<Food> = newSuspendedTransaction {
        Foods.select { Foods.name eq name }.map { it.toFood() }
    }
}