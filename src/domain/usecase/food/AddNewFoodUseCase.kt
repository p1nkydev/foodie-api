package com.foodie.api.domain.usecase.food

import com.foodie.api.domain.model.food.FoodCreating
import com.foodie.api.domain.repository.FoodRepository
import domain.usecase.base.BaseUseCase

class AddNewFoodUseCase(
    private val repository: FoodRepository
) : BaseUseCase<FoodCreating, Unit>() {

    override suspend fun execute(params: FoodCreating) {
        repository.addNewFood(params)
    }
}