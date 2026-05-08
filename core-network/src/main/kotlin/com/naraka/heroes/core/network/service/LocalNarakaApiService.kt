package com.naraka.heroes.core.network.service

import android.content.Context
import com.naraka.heroes.core.network.model.HeroResponse
import com.skydoves.sandwich.ApiResponse
import com.squareup.moshi.Moshi
import org.json.JSONArray
import timber.log.Timber
import javax.inject.Inject

class LocalNarakaApiService @Inject constructor(
    private val context: Context,
    private val moshi: Moshi
) : NarakaApiService {

    override suspend fun fetchHeroList(): ApiResponse<List<HeroResponse>> {
        return try {
            Timber.d("LocalApi: fetching hero list")
            val indexJson = context.assets.open("heroes/index.json")
                .bufferedReader().use { it.readText() }
            val keys = JSONArray(indexJson)
            Timber.d("LocalApi: keys found: ${keys.length()}")

            val heroes = mutableListOf<HeroResponse>()
            val adapter = moshi.adapter(HeroResponse::class.java)

            for (i in 0 until keys.length()) {
                val key = keys.getString(i)
                try {
                    val heroJson = context.assets.open("heroes/$key.json")
                        .bufferedReader().use { it.readText() }
                    adapter.fromJson(heroJson)?.let { response ->
                        // Automatically map the portrait_url to local assets if it's a remote URL or empty
                        val localResponse = if (response.drawableName.isNotBlank()) {
                            response.copy(
                                portraitUrl = "file:///android_asset/heroes/images/${response.drawableName}.jpg",
                                avatarUrl = "file:///android_asset/heroes/images/${response.drawableName}.jpg"
                            )
                        } else response
                        heroes.add(localResponse)
                    }
                } catch (e: Exception) {
                    Timber.e(e, "LocalApi: failed to parse hero $key")
                }
            }
            Timber.d("LocalApi: heroes parsed: ${heroes.size}")
            ApiResponse.Success(heroes)
        } catch (e: Exception) {
            Timber.e(e, "LocalApi: critical error")
            ApiResponse.Failure.Exception(e)
        }
    }
}
