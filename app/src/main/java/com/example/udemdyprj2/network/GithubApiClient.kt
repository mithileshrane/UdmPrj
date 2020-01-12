package com.example.udemdyprj2.network

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object GithubApiClient {

    val BASE_URL="https://api.github.com/"
    val githubService:GithubService

    init {
        val httpInterceptor=HttpLoggingInterceptor()
        httpInterceptor.level=HttpLoggingInterceptor.Level.BODY

        val interceptor = Interceptor { chain ->
            val newRequest = chain.request().newBuilder()

                .method(chain.request().method(), chain.request().body())
                .build()


            chain.proceed(newRequest)
        }



        val okHttpClient=OkHttpClient.Builder()
            .addInterceptor(httpInterceptor)
            .addInterceptor(interceptor)
            .readTimeout(300,TimeUnit.SECONDS)
            .connectTimeout(300,TimeUnit.SECONDS)
            .writeTimeout(300,TimeUnit.SECONDS)
            .build()

        val retrofit=Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        githubService=retrofit.create(GithubService::class.java)
    }

    fun getGithubServiceInst():GithubService= githubService
}