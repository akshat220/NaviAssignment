package com.example.assignment

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("repos/akshat220/Epifi-Assignment/pulls")
    fun prList(@Query("state") state: String?): Call<List<PrDataResponse>>
}