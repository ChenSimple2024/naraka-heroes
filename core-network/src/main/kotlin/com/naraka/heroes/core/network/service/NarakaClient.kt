package com.naraka.heroes.core.network.service

import com.naraka.heroes.core.network.model.HeroResponse
import com.skydoves.sandwich.ApiResponse
import javax.inject.Inject

class NarakaClient @Inject constructor(
    private val narakaApiService: NarakaApiService
) {
    suspend fun fetchHeroList(): ApiResponse<List<HeroResponse>> =
        narakaApiService.fetchHeroList()
}
