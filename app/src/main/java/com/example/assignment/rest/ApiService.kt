package com.example.assignment.rest

import com.example.assignment.model.PrDataResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("repos/akshat220/NaviAssignment/pulls")
    suspend fun prList(@Query("state") state: String?): Response<List<PrDataResponse>>
}