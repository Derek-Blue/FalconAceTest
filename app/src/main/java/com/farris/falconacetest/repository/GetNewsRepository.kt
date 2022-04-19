package com.farris.falconacetest.repository

interface GetNewsRepository {

    suspend operator fun invoke(): Result<List<RepositoryItem>>
}