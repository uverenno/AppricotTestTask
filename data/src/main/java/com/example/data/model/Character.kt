package com.example.data.model

data class Character(
    val id : Int,
    val name : String,
    val status : String,
    val species : String,
    val gender : String,
    val location : Location,
    val image : String,
    val episode : List<String>
)