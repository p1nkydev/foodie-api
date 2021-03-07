package com.foodie.api.domain.usecase.food

import com.foodie.api.domain.model.food.Food
import com.foodie.api.domain.model.user.User
import com.foodie.api.domain.repository.FoodRepository
import domain.usecase.base.BaseUseCase

class GetUserExistingFoodUseCase(
    private val foodRepository: FoodRepository
) : BaseUseCase<User, List<Food>>() {

    override suspend fun execute(params: User): List<Food> {
        return foodRepository.getExistingFoodForUser(params)
    }

}