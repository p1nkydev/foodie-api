package com.foodie.api.domain.model.food

import kotlinx.serialization.Serializable

@Serializable
data class Food(val id: Long, val name: String)