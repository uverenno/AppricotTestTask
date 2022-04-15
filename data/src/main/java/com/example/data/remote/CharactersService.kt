package com.example.data.remote

import com.example.data.model.Character
import com.example.data.model.Characters
import com.skydoves.sandwich.ApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharactersService {

    @GET("/api/character/{id}")
    suspend fun getCharacter(@Path("id") id: Int): ApiResponse<Character>


    @GET("/api/character/")
    suspend fun getAllCharacters(
        @Query("page") page: Int,
    ): Response<Characters>

}