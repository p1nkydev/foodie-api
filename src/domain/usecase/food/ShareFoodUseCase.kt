package com.foodie.api.domain.usecase.food

import com.foodie.api.domain.model.food.FoodSharing
import com.foodie.api.domain.repository.FoodRepository
import domain.usecase.base.BaseUseCase

class ShareFoodUseCase(
    private val foodRepository: FoodRepository
) : BaseUseCase<FoodSharing, FoodSharing>() {

    override suspend fun execute(params: FoodSharing): FoodSharing {
        return foodRepository.shareFood(params)
    }
}