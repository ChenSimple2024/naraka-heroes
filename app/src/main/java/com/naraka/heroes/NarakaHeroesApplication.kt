package com.naraka.heroes

import android.app.Application
import coil.Coil
import coil.ImageLoader
import dagger.hilt.android.HiltAndroidApp
import okhttp3.OkHttpClient

@HiltAndroidApp
class NarakaHeroesApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // 配置 Coil 使用支持重定向的 OkHttpClient（fandom wiki 图片 URL 会重定向）
        val imageLoader = ImageLoader.Builder(this)
            .okHttpClient {
                OkHttpClient.Builder()
                    .followRedirects(true)
                    .followSslRedirects(true)
                    .addInterceptor { chain ->
                        chain.proceed(
                            chain.request().newBuilder()
                                .header("User-Agent", "Mozilla/5.0 (Android)")
                                .header("Referer", "https://narakabladepoint.fandom.com/")
                                .build()
                        )
                    }
                    .build()
            }
            .build()
        Coil.setImageLoader(imageLoader)
    }
}
