package com.franco.rickandmorty_xml.domain.usecase

import com.apollographql.apollo3.api.ApolloResponse
import com.franco.rickandmorty_xml.CharacterListQuery
import com.franco.rickandmorty_xml.domain.repository.CharactersRepository

class GetCharactersUseCase(
    private val repository: CharactersRepository
) {

    suspend fun invoke(page: Int): ApolloResponse<CharacterListQuery.Data> =
        repository.getCharacters(page)
}