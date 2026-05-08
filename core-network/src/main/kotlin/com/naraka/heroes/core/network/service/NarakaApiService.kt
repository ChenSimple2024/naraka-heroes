package com.naraka.heroes.core.network.service

import com.naraka.heroes.core.network.model.HeroResponse
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET

interface NarakaApiService {
    @GET("heroes")
    suspend fun fetchHeroList(): ApiResponse<List<HeroResponse>>
}
