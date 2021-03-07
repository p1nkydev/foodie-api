package com.foodie.api.domain.model.food

import kotlinx.serialization.Serializable

@Serializable
data class FoodCreating(val name: String, val amount: String)