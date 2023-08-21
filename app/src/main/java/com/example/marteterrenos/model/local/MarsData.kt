package com.example.marteterrenos.model.local


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
@Entity(tableName = "mars_table")
data class MarsData (
    @PrimaryKey
    val id: String,
    val price: Long,
    val type: String,
    @SerializedName("img_src")
    val img: String
)