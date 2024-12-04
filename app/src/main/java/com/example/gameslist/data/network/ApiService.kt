package com.example.gameslist.data.network

import com.example.example.GameResponse
import okhttp3.internal.platform.Platform
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("api/games")
    suspend fun getGames(): List<GameResponse>
}
object RetrofitInstance{
    val retrofit = Retrofit.Builder()
        .baseUrl("https://www.freetogame.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val apiService=retrofit.create(ApiService::class.java)
}