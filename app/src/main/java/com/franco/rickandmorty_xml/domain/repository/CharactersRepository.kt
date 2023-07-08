package com.franco.rickandmorty_xml.domain.repository

import com.apollographql.apollo3.api.ApolloResponse
import com.franco.rickandmorty_xml.CharacterListQuery
import com.franco.rickandmorty_xml.domain.model.InfoCharacter

interface CharactersRepository {
    suspend fun getCharacters(page: Int): ApolloResponse<CharacterListQuery.Data>
    suspend fun getSingleCharacter(id: String): InfoCharacter?
}