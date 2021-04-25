package com.foodie.api.domain.usecase.food

import com.foodie.api.domain.model.food.AvailableFood
import com.foodie.api.domain.model.user.User
import com.foodie.api.domain.repository.FoodRepository
import domain.usecase.base.BaseUseCase

class GetUserExistingFoodUseCase(
    private val foodRepository: FoodRepository
) : BaseUseCase<User, List<AvailableFood>>() {

    override suspend fun execute(params: User): List<AvailableFood> {
        return foodRepository.getExistingFoodForUser(params)
    }

}