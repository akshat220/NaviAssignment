package com.example.assignment

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("repos/akshat220/Epifi-Assignment/pulls")
    suspend fun prList(@Query("state") state: String?): Response<List<PrDataResponse>>
}