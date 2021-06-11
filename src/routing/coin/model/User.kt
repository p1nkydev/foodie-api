package com.foodie.api.routing.coin.model

import kotlinx.serialization.Serializable
import kotlin.random.Random.Default.nextInt

@Serializable
data class User(
    val id: Int = nextInt(),
    val name: String = "",
    val balance: Int = 100
)