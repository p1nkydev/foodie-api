package com.foodie.api.domain.model.auth

import kotlinx.serialization.Serializable

@Serializable
data class Token(val value: String)