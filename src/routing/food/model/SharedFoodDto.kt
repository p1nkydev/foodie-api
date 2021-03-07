package com.foodie.api.routing.food.model

import kotlinx.serialization.Serializable

@Serializable
class ShareFoodRequest(val foodPropertyId: Long, val roomId: Long)