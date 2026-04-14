package com.naraka.heroes.network

import android.content.Context
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import org.json.JSONArray

/**
 * 从 assets/heroes/ 目录读取英雄数据：
 * 1. 读取 heroes/index.json 获取所有英雄 key 列表
 * 2. 逐个读取 heroes/{key}.json
 * 3. 合并成 JSON 数组返回
 *
 * 新增英雄只需：
 * - 在 assets/heroes/ 下新建 {key}.json
 * - 在 assets/heroes/index.json 中添加该 key
 */
class MockAssetInterceptor(private val context: Context) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val json = buildHeroesJson()
        return Response.Builder()
            .code(200)
            .message("OK")
            .request(chain.request())
            .protocol(Protocol.HTTP_1_1)
            .body(json.toResponseBody("application/json".toMediaTypeOrNull()))
            .build()
    }

    private fun buildHeroesJson(): String {
        // 读取 index.json 获取英雄 key 列表
        val indexJson = context.assets.open("heroes/index.json")
            .bufferedReader().use { it.readText() }
        val keys = JSONArray(indexJson)

        // 逐个读取英雄 JSON，合并成数组
        // 使用 JSONArray 确保合并后的 JSON 格式正确
        val resultArray = JSONArray()
        for (i in 0 until keys.length()) {
            val key = keys.getString(i)
            try {
                val heroJson = context.assets.open("heroes/$key.json")
                    .bufferedReader().use { it.readText().trim() }
                val heroObj = org.json.JSONObject(heroJson)
                resultArray.put(heroObj)
            } catch (e: Exception) {
                // 跳过读取或解析失败的英雄文件
            }
        }
        return resultArray.toString()
    }
}
