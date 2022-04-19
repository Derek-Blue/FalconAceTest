package com.farris.falconacetest.di

import com.farris.falconacetest.service.NewsService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.ConnectionSpec
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val NewsServerUrl = "https://static.mixerbox.com/"

val appModule = module {

    single {
        createGson()
    }

    single {
        provideRetrofit(get())
    }

    single<NewsService> {
        get<Retrofit>().create(NewsService::class.java)
    }

}

private fun provideRetrofit(gson: Gson): Retrofit {
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
