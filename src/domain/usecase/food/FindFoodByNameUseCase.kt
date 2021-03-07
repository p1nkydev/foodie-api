package com.foodie.api.domain.usecase.food

import com.foodie.api.domain.model.food.Food
import com.foodie.api.domain.repository.FoodRepository
import domain.usecase.base.BaseUseCase

class FindFoodByNameUseCase(
    private val foodRepository: FoodRepository
) : BaseUseCase<String, List<Food>>() {

    override suspend fun execute(params: String): List<Food> {
        return foodRepository.findFoodByName(params)
    }
}