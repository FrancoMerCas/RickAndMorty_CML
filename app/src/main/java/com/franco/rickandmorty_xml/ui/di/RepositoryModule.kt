package com.franco.rickandmorty_xml.ui.di

import com.apollographql.apollo3.ApolloClient
import com.franco.rickandmorty_xml.data.repository.CharactersRepositoryImpl
import com.franco.rickandmorty_xml.domain.repository.CharactersRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideCharacterRepository(apolloClient: ApolloClient): CharactersRepository {
        return CharactersRepositoryImpl(apolloClient)
    }
}