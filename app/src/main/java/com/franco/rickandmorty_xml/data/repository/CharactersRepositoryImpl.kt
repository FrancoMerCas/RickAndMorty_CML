package com.franco.rickandmorty_xml.data.repository

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.ApolloResponse
import com.apollographql.apollo3.api.Optional
import com.franco.rickandmorty_xml.CharacterInfoQuery
import com.franco.rickandmorty_xml.CharacterListQuery
import com.franco.rickandmorty_xml.data.utils.toModel
import com.franco.rickandmorty_xml.domain.model.InfoCharacter
import com.franco.rickandmorty_xml.domain.repository.CharactersRepository

class CharactersRepositoryImpl(
    private val apolloClient: ApolloClient
) : CharactersRepository {

    override suspend fun getCharacters(page: Int): ApolloResponse<CharacterListQuery.Data> {
        return apolloClient
            .query(
                CharacterListQuery(Optional.present(page))
            )
            .execute()
    }


    override suspend fun getSingleCharacter(id: String): InfoCharacter? {
        return apolloClient
            .query(CharacterInfoQuery(id))
            .execute()
            .data
            ?.character
            ?.toModel()
    }
}