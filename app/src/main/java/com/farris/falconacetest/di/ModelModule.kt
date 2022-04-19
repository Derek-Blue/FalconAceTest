package com.farris.falconacetest.di

import com.farris.falconacetest.repository.GetNewsRepository
import com.farris.falconacetest.repository.GetNewsRepositoryImpl
import com.farris.falconacetest.usecase.GetNewsUseCase
import com.farris.falconacetest.usecase.GetNewsUseCaseImpl
import org.koin.dsl.module

val modelModule = module {
    factory<GetNewsRepository> { GetNewsRepositoryImpl(get()) }
    factory<GetNewsUseCase> { GetNewsUseCaseImpl(get()) }
}