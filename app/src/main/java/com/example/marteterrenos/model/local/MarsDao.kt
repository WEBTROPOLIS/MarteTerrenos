package com.example.marteterrenos.model.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MarsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertState(state : MarsData)

    @Query("SELECT * FROM MARS_TABLE order by id")
    fun getAllState(): LiveData<List<MarsData>>

    @Query("SELECT * FROM mars_table WHERE id = :idState")
    suspend fun getOneState(idState : String) : MarsData?

}