package domain.usecase

import domain.model.auth.RegisterModel
import domain.repository.AuthRepository
import domain.usecase.base.BaseUseCase

class RegisterUserUseCase(private val repository: AuthRepository) : BaseUseCase<RegisterModel, Unit>() {
    override suspend fun execute(params: RegisterModel) {
        repository.registerUser(params)
    }
}