package com.farris.falconacetest.service

import com.farris.falconacetest.service.response.ResponseGetNews
import retrofit2.Response
import retrofit2.http.GET

interface NewsService {

    @GET("interview/interview_get_vector.json")
    suspend fun getNews(): Response<ResponseGetNews>
}