package com.foodie.api.data.datasource.dao

import com.foodie.api.data.database.table.FoodProperties
import com.foodie.api.data.database.table.Foods
import com.foodie.api.data.database.table.RoomFoodProperties
import com.foodie.api.data.database.table.RoomParticipants
import com.foodie.api.data.mapper.toFood
import com.foodie.api.domain.model.food.*
import com.foodie.api.domain.model.user.User
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

class FoodDao {

    suspend fun getAvailableFoodFor(user: User): List<AvailableFood> {
        return newSuspendedTransaction {
            RoomParticipants
                .select { RoomParticipants.participantId eq user.id }
                .map { it[RoomParticipants.roomId] }
                .map {
                    val foodProperties = RoomFoodProperties
                        .select { RoomFoodProperties.roomId eq it }
                        .map { it[RoomFoodProperties.foodPropertyId] }
                        .map { foodPropertyId ->
                            FoodProperties.select { FoodProperties.id eq foodPropertyId }.map { it.toFoodProperty() }
                                .first()
                        }

                    foodProperties.map {
                        val food = Foods.select { Foods.id eq it.foodId }.first().toFood()
                        AvailableFood(food.id, food.name, food.description, it.amountText, it.amountPercent, it.buyDate)
                    }
                }.flatten()
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

fun ResultRow.toFoodProperty() = FoodProperty(
    this[FoodProperties.id],
    this[FoodProperties.foodId],
    this[FoodProperties.amountText],
    this[FoodProperties.sharedAmountPercent],
    this[FoodProperties.buyDate],
)
