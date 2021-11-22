package com.example.harrypotter.data.model


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.harrypotter.domain.model.Characters
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity
data class CharactersItem(
    @ColumnInfo(name = "actor")
    @SerializedName("actor")
    val actor: String,

    @ColumnInfo(name = "alive")
    @SerializedName("alive")
    val alive: Boolean,

    @ColumnInfo(name = "ancestry")
    @SerializedName("ancestry")
    val ancestry: String,

    @ColumnInfo(name = "dateOfBirth")
    @SerializedName("dateOfBirth")
    val dateOfBirth: String,

    @ColumnInfo(name = "eyeColour")
    @SerializedName("eyeColour")
    val eyeColour: String,

    @ColumnInfo(name = "gender")
    @SerializedName("gender")
    val gender: String,

    @ColumnInfo(name = "hairColour")
    @SerializedName("hairColour")
    val hairColour: String,

    @ColumnInfo(name = "hogwartsStaff")
    @SerializedName("hogwartsStaff")
    val hogwartsStaff: Boolean,

    @ColumnInfo(name = "hogwartsStudent")
    @SerializedName("hogwartsStudent")
    val hogwartsStudent: Boolean,

    @ColumnInfo(name = "house")
    @SerializedName("house")
    val house: String,

    @ColumnInfo(name = "image")
    @SerializedName("image")
    val image: String,

    @ColumnInfo(name = "name")
    @SerializedName("name")
    val name: String,

    @ColumnInfo(name = "patronus")
    @SerializedName("patronus")
    val patronus: String,

    @ColumnInfo(name = "species")
    @SerializedName("species")
    val species: String,

    @ColumnInfo(name = "yearOfBirth")
    @SerializedName("yearOfBirth")
    val yearOfBirth: String,

    @ColumnInfo(name = "flag")
    @Expose
    var flag : Boolean = false,

)
    {
        @PrimaryKey(autoGenerate = true)
        var uuid : Int = 0
    }

fun CharactersItem.toCharacters() : Characters{
    return Characters(actor,ancestry,house, image, name, patronus)
}