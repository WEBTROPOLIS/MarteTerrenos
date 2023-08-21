package com.example.marteterrenos.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.marteterrenos.model.local.MarsData
import com.example.marteterrenos.model.local.MarsDataBase
import com.example.marteterrenos.model.local.MarsRepository

class MarsViewModel(application: Application): AndroidViewModel(application) {

    private val marsRepository : MarsRepository =
        MarsRepository(MarsDataBase.getDataBase(application).getMarsDao())

    fun getAllState() : LiveData<List<MarsData>>{
        return marsRepository.marsLiveData
    }

    suspend fun getOneState(stateId : String) : MarsData? {
        return marsRepository.getOneState(stateId)
    }


}