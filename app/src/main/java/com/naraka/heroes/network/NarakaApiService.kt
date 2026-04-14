package com.naraka.heroes.network

import com.naraka.heroes.model.HeroResponse
import retrofit2.http.GET

interface NarakaApiService {
    @GET("heroes")
    suspend fun fetchHeroes(): List<HeroResponse>
}
