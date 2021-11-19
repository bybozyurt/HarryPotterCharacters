package com.example.harrypotter.domain.model

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Ahmet Bozyurt on 19.11.2021
 */
data class Characters(
    val actor: String,
    val ancestry: String,
    val house: String,
    val image: String,
    val name: String,
    val patronus: String
)


