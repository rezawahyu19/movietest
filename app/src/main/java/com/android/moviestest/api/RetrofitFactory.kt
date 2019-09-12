package com.android.moviestest.api

import com.android.moviestest.App
import com.android.moviestest.util.JNIUtil
import com.ashokvarma.gander.GanderInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitFactory {

    fun retrofitService(): ApiService {
        return Retrofit.Builder()
            .baseUrl(JNIUtil.apiURL)
            .client(okHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ApiService::class.java)
    }

    fun okHttpClient(): OkHttpClient {
        val okHttpBuild = OkHttpClient.Builder()
        okHttpBuild.connectTimeout(1, TimeUnit.MINUTES)
        okHttpBuild.readTimeout(1, TimeUnit.MINUTES)
        okHttpBuild.writeTimeout(1, TimeUnit.MINUTES)
        okHttpBuild.addInterceptor(ApiInterceptor())
        okHttpBuild.addInterceptor(
            GanderInterceptor(App.context)
                .showNotification(true)
        )
        return okHttpBuild.build()
    }
}