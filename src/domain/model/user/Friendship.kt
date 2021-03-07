package com.foodie.api.domain.model.user

import kotlinx.serialization.Serializable

@Serializable
class Friendship(val user: Friend, val friend: Friend)