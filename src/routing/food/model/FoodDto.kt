package com.foodie.api.routing.food.model

import kotlinx.serialization.Serializable

@Serializable
data class CreateFoodRequest(val name: String, val amount: String)