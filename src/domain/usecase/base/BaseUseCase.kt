package domain.usecase.base

abstract class BaseUseCase<P, R> {
    abstract suspend fun execute(params: P): R
}