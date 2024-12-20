package com.example.snapscape.network

import com.example.snapscape.model.UnsplashImageDTO
import com.example.snapscape.model.unsplashimagedto
import kotlinx.serialization.json.Json
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Response

private const val  apiKey = CONSTANTS.apiKey
private const val BASE_URL="https://api.unsplash.com/"
interface ApiService {
    @Headers("Authorization: Client-ID ${apiKey}")
    @GET("photos/")
    suspend fun getPhotos(): Response<List<UnsplashImageDTO>>
}
private val retrofit : Retrofit= Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(BASE_URL).build()

object api{ val retrofitservice by lazy { retrofit.create(ApiService::class.java) }}