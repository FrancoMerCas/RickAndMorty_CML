package com.franco.rickandmorty_xml.ui.di

import com.franco.rickandmorty_xml.domain.repository.CharactersRepository
import com.franco.rickandmorty_xml.domain.usecase.GetCharactersUseCase
import com.franco.rickandmorty_xml.domain.usecase.GetSingleCharacterUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    @Singleton
    fun provideGetCharactersUseCase(
        repository: CharactersRepository
    ): GetCharactersUseCase {
        return GetCharactersUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetSingleCharacterUseCase(
        repository: CharactersRepository
    ): GetSingleCharacterUseCase {
        return GetSingleCharacterUseCase(repository)
    }
}