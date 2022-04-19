package com.farris.falconacetest.di

import com.farris.falconacetest.repository.GetNewsRepository
import com.farris.falconacetest.repository.GetNewsRepositoryImpl
import org.koin.dsl.module

val modelModule = module {
    factory<GetNewsRepository> { GetNewsRepositoryImpl(get()) }
}