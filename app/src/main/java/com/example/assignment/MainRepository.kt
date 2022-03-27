package com.example.assignment

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

object MainRepository {

    var apiService: ApiService? = null

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .build()
        apiService = retrofit.create(ApiService::class.java)
    }

    suspend fun submitData(): List<PrDataResponse>? {
        var data: List<PrDataResponse>? = null
       apiService?.prList("closed")?.enqueue(object : Callback<List<PrDataResponse>> {
           override fun onResponse(call: Call<List<PrDataResponse>>, response: Response<List<PrDataResponse>>) {
               data = response.body()
               Log.d("DataResponse", data.toString())
           }

           override fun onFailure(call: Call<List<PrDataResponse>>, t: Throwable) {
               Log.d("DataResponse", t.toString())
               call.cancel()
           }
       })
        return data
    }
}