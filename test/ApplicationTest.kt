package com.foodie.api

import com.foodie.api.domain.model.food.FoodCreating
import com.foodie.api.routing.food.model.ShareFoodRequest
import domain.model.auth.AuthModel
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.test.Test

class ApplicationTest {

    private val json = Json { isLenient = true }


    @Test
    fun testRoot() {
        withTestApplication({ module(testing = true) }) {
            val client = client
            runBlocking {
                val authKirill = AuthModel("kirill", "qq")
                val authLena = AuthModel("lena", "qqa")
                val newFood = FoodCreating("borsh", "dohuya")
                val shareRequest = ShareFoodRequest(1, 1)

                val kirillToken = client.post<String>(path = "api/v1/authorize", body = json.encodeToString(authKirill)) {
                    contentType(ContentType.Application.Json)
                }

                val lenaToken = client.post<String>(path = "api/v1/authorize", body = json.encodeToString(authLena)) {
                    contentType(ContentType.Application.Json)
                }


                val create = client.post<String>(path = "api/v1/food/add", body = json.encodeToString(newFood)) {
                    contentType(ContentType.Application.Json)
                    headers {
                        append("Authorization", "Basic $kirillToken")
                    }
                }

                val share = client.post<String>(path = "api/v1/food/share", body = json.encodeToString(shareRequest)) {
                    contentType(ContentType.Application.Json)
                    headers {
                        append("Authorization", "Basic $kirillToken")
                    }
                }


                val data = client.get<String>("api/v1/food/existing") {
                    headers {
                        append("Authorization", "Basic $lenaToken")
                    }
                }

                println(data)
            }
        }
    }
}
