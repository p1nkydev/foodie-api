package com.foodie.api

import domain.model.auth.AuthModel
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.server.testing.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals

class ApplicationTest {
    @Test
    fun testRoot() {
        withTestApplication({ module(testing = true) }) {
            handleRequest(HttpMethod.Post, "api/v1/authorize").apply {
                val json = Json { isLenient = true }
                val auth = AuthModel("name", "pass")
                val requestJson = json.encodeToString(auth)
                request.setBody(requestJson)

                assertEquals(HttpStatusCode.OK, response.status())
                assertEquals("HELLO WORLD!", response.content)
            }
        }
    }
}
