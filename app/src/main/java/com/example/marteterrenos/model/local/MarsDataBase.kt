package com.example.marteterrenos.model.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [MarsData::class], version = 1, exportSchema = false)
abstract class MarsDataBase : RoomDatabase() {
    abstract fun getMarsDao() : MarsDao
    companion object{
        @Volatile
        private var
                INSTANCE : MarsDataBase? = null
        fun getDataBase(context: Context) : MarsDataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MarsDataBase::class.java, "PlantDatabase")
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}