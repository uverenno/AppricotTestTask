package com.example.data.repository.details

import com.example.data.model.Character
import com.skydoves.sandwich.ApiResponse

interface DetailsRepository {

    suspend fun getCharacter(id : Int): ApiResponse<Character>

}