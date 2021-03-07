package com.foodie.api.routing.food


import com.foodie.api.domain.model.food.FoodCreating
import com.foodie.api.domain.model.food.FoodSharing
import com.foodie.api.domain.usecase.food.AddNewFoodUseCase
import com.foodie.api.domain.usecase.food.FindFoodByNameUseCase
import com.foodie.api.domain.usecase.food.GetUserExistingFoodUseCase
import com.foodie.api.domain.usecase.food.ShareFoodUseCase
import com.foodie.api.routing.food.model.CreateFoodRequest
import com.foodie.api.routing.food.model.ShareFoodRequest
import com.foodie.api.routing.session
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.koin.ktor.ext.inject


fun Route.foodApi() {

    val getUserExistingFoodUseCase by inject<GetUserExistingFoodUseCase>()
    val shareFoodUseCase by inject<ShareFoodUseCase>()
    val addNewFood by inject<AddNewFoodUseCase>()
    val findFoodByName by inject<FindFoodByNameUseCase>()


    get("api/v1/food/search") {
        val query = call.parameters["query"].orEmpty()
        call.respond(findFoodByName.execute(query))
    }

    get("api/v1/food/existing") {
        val foods = getUserExistingFoodUseCase.execute(call.session.user)
        call.respond(foods)
    }

    post("api/v1/food/add") {
        val createFood = call.receive<CreateFoodRequest>()
        addNewFood.execute(FoodCreating(createFood.name, createFood.amount))
        call.respond(HttpStatusCode.OK)
    }

    post("api/v1/food/share") {
        val shareRequest = call.receive<ShareFoodRequest>()
        val sharing = FoodSharing(
            foodPropertyId = shareRequest.foodPropertyId,
            roomId = shareRequest.roomId
        )
        call.respond(shareFoodUseCase.execute(sharing))
    }

    get("api/v1/food/recommended") {

    }
}