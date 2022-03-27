package com.example.assignment.rest

import com.example.assignment.model.PrDataResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MainRepository {

    private var apiService: ApiService? = null

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        apiService = retrofit.create(ApiService::class.java)
    }

    suspend fun getPrDetails(): List<PrDataResponse>? {
        return apiService?.prList("closed")?.body()
    }
}