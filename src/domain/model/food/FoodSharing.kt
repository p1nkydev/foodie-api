package com.foodie.api.domain.model.food

import kotlinx.serialization.Serializable

@Serializable
class FoodSharing(val foodPropertyId: Long, val roomId: Long)