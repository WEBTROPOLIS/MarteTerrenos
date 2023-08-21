package com.example.marteterrenos.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.marteterrenos.databinding.ActivityMainBinding
import com.example.marteterrenos.model.local.MarsDataBase
import com.example.marteterrenos.model.local.MarsRepository
import com.example.marteterrenos.remote.RetrofitRepository
import com.example.marteterrenos.remote.RetrofitViewmodel
import com.example.marteterrenos.remote.RetrofitViewmodelFactory

class MainActivity : AppCompatActivity() {


    private lateinit var mBinding: ActivityMainBinding
    private lateinit var retrofitRepository: RetrofitRepository
    private lateinit var retrofitViewmodel: RetrofitViewmodel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        val marsDao = MarsDataBase.getDataBase(application).getMarsDao()
        val marsRepository = MarsRepository(marsDao)
        retrofitRepository = RetrofitRepository(marsRepository)

        retrofitViewmodel = ViewModelProvider(this,
            RetrofitViewmodelFactory(application,retrofitRepository)
        )[RetrofitViewmodel::class.java]

        retrofitViewmodel.fecthAndInsertData()

        launchFragment()
    }

    private fun launchFragment(){
        supportFragmentManager.beginTransaction()
            .replace(mBinding.frame.id,StateFragment())
            .addToBackStack(null)
            .commit()
    }
}