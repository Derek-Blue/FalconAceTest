package com.farris.falconacetest.di

import com.farris.falconacetest.repository.GetNewsRepository
import com.farris.falconacetest.repository.GetNewsRepositoryImpl
import com.farris.falconacetest.usecase.GetNewsUseCase
import com.farris.falconacetest.usecase.GetNewsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier

// 多繼承的 named寫法
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class BindGetNewsRepository

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class BindGetNewsRepository2
//

@Module
@InstallIn(SingletonComponent::class)
abstract class HiltModelModule {

    @Binds
    abstract fun bindGetNewsRepository(impl: GetNewsRepositoryImpl): GetNewsRepository

    @Binds
    abstract fun bindGetNewsUseCase(impl: GetNewsUseCaseImpl): GetNewsUseCase
}