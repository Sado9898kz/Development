package com.home.work.placegoat.date.remote

import com.google.gson.GsonBuilder
import com.home.work.placegoat.ServesRic
import com.home.work.placegoat.pojo.ServesCharacterOrigin
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface CharacterApi {
    @GET("/api/character")
    fun getCharacterAsync(): Deferred<ServesRic>

    @GET("/api/location/{id}")
    fun getCharacterOriginAsync(
        @Path("id")
        id: String
    ): Deferred<ServesCharacterOrigin>

    companion object {
        operator fun invoke(): Retrofit {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build()

            val gson = GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://rickandmortyapi.com")
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        }
    }
}