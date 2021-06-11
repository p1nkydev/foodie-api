package com.foodie.api.routing.coin.model

import kotlinx.serialization.Serializable

@Serializable
data class UserCredentials(val name: String, val password: String)
