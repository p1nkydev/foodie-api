package com.foodie.api.domain.model.user

import kotlinx.serialization.Serializable

@Serializable
data class Friend(val id: Long, val name: String)