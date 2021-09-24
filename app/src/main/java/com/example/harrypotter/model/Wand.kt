package com.example.harrypotter.model


import com.google.gson.annotations.SerializedName

data class Wand(
    @SerializedName("core")
    val core: String,
    @SerializedName("length")
    val length: String,
    @SerializedName("wood")
    val wood: String
)