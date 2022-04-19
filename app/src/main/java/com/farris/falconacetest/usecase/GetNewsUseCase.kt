package com.farris.falconacetest.usecase

interface GetNewsUseCase {

    /**
     * Key: Category , Value : Items
     */
    suspend operator fun invoke(): Result<Map<String, List<UseCaseNews>>>
}