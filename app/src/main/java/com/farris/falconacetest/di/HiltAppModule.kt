package com.farris.falconacetest.di

import com.farris.falconacetest.service.NewsService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.internal.managers.ApplicationComponentManager
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.ConnectionSpec
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class HiltAppModule {

    companion object {
        private const val NewsServerUrl = "https://static.mixerbox.com/"
    }

    @Provides
    @Singleton
    fun provideGson(): Gson = createGson()

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson): Retrofit = createRetrofit(gson)

    @Provides
    @Singleton
    fun provideNewsService(retrofit: Retrofit): NewsService = retrofit.create(NewsService::class.java)

    private fun createRetrofit(gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl(NewsServerUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(createOkHttpClient())
            .build()
    }

    private fun createOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectionSpecs(listOf(ConnectionSpec.COMPATIBLE_TLS, ConnectionSpec.CLEARTEXT))
            .connectTimeout(30L, TimeUnit.SECONDS)
            .callTimeout(30L, TimeUnit.SECONDS)
            .readTimeout(30L, TimeUnit.SECONDS)
            .writeTimeout(30L, TimeUnit.SECONDS)
            .build()
    }

    private fun createGson(): Gson {
        return GsonBuilder()
            .serializeNulls()
            .setLenient()
            .serializeSpecialFloatingPointValues()
            .enableComplexMapKeySerialization()
            .create()
    }
}