package com.example.marteterrenos.model.local

import com.example.marteterrenos.model.local.MarsDao
import com.example.marteterrenos.model.local.MarsData

class MarsRepository(private val marsDao: MarsDao) {
     val marsLiveData = marsDao.getAllState()

    suspend fun insertState(state : MarsData) { marsDao.insertState(state)}

    suspend fun getOneState(idState : String) : MarsData? {
        return marsDao.getOneState(idState)
    }

}