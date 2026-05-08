package com.naraka.heroes.core.network.di

import android.content.Context
import com.naraka.heroes.core.network.service.LocalNarakaApiService
import com.naraka.heroes.core.network.service.NarakaApiService
import com.skydoves.sandwich.retrofit.adapters.ApiResponseCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class LocalApi

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RemoteApi

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    @Provides
    @Singleton
    @RemoteApi
    fun provideRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://api.naraka.com/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(ApiResponseCallAdapterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    @RemoteApi
    fun provideNarakaApiService(@RemoteApi retrofit: Retrofit): NarakaApiService {
        return retrofit.create(NarakaApiService::class.java)
    }

    @Provides
    @Singleton
    @LocalApi
    fun provideLocalNarakaApiService(
        @ApplicationContext context: Context,
        moshi: Moshi
    ): NarakaApiService {
        return LocalNarakaApiService(context, moshi)
    }

    /**
     * Default NarakaApiService. 
     * You can easily switch between Local and Remote here.
     */
    @Provides
    @Singleton
    fun provideDefaultNarakaApiService(@LocalApi localApi: NarakaApiService): NarakaApiService {
        return localApi
    }
}
