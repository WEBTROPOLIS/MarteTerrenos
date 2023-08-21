package com.example.marteterrenos.remote

import com.example.marteterrenos.model.local.MarsData
import retrofit2.Response
import retrofit2.http.GET

interface MarsApi {

    @GET("realestate")
    suspend fun fetchStateData(): Response<List<MarsData>>

}