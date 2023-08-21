package com.example.marteterrenos.remote

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class RetrofitViewmodel(
    application: Application,
    private val retrofitRepository: RetrofitRepository
) : AndroidViewModel(application) {

    fun fecthAndInsertData()  {
        viewModelScope.launch { retrofitRepository.fetchStateDataAndInsert() }
    }
}