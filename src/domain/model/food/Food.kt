package com.foodie.api.domain.model.food

import kotlinx.serialization.Serializable

@Serializable
data class Food(val id: Long, val name: String, val description: String)

@Serializable
data class FoodProperty(
    val id: Long,
    val foodId: Long,
    val amountText: String,
    val amountPercent: Int,
    val buyDate: Long,
)

@Serializable
data class AvailableFood(
    val id: Long,
    val name: String,
    val description: String,
    val amount: String,
    val amountPercent: Int,
    val buyDate: Long
)