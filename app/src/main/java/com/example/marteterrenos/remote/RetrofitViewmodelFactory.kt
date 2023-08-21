package com.example.marteterrenos.remote

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class RetrofitViewmodelFactory(
    private val application: Application,
    private val retrofitRepository: RetrofitRepository
) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RetrofitViewmodel::class.java)) {
            return RetrofitViewmodel(application,retrofitRepository) as T
        }
        throw  IllegalArgumentException("Unknown ViewModel")
    }
}