package com.example.data.repository.details

import com.example.data.remote.CharactersService

class DetailsRepositoryImpl(
    private val charactersService: CharactersService
    ) : DetailsRepository {

    override suspend fun getCharacter(id: Int) = charactersService.getCharacter(id)

}