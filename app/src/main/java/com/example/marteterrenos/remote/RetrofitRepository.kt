package com.example.marteterrenos.remote

import com.example.marteterrenos.model.local.MarsData
import com.example.marteterrenos.model.local.MarsRepository

class RetrofitRepository(private val marsRepository: MarsRepository) {

    private val apiService = RetrofitClient.getRetrofit()

    suspend fun fetchStateDataAndInsert(){
        try {
            val response = apiService.fetchStateData()

            if (response.isSuccessful){
                val marsList = response.body()

                if (marsList != null){
                    for (state in marsList){
                        insertState(state)
                    }
                }
            }
        } catch (e: Exception){}
    }

    suspend fun insertState(state : MarsData) {
        marsRepository.insertState(state)
    }
}