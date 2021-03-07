package com.foodie.api.routing

import domain.model.auth.Session
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.features.*

val ApplicationCall.session : Session
    get() = this.authentication.principal() ?: throw NotFoundException()