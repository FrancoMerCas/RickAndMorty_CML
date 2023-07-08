package com.franco.rickandmorty_xml.ui.di

import com.franco.rickandmorty_xml.domain.usecase.GetCharactersUseCase
import com.franco.rickandmorty_xml.domain.usecase.GetSingleCharacterUseCase
import com.franco.rickandmorty_xml.ui.viewmodel.MainViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FactoryModule {

    @Singleton
    @Provides
    fun provideFactory(
        getCharactersUseCase: GetCharactersUseCase,
        getSingleCharacterUseCase: GetSingleCharacterUseCase
    ) : MainViewModelFactory {
        return MainViewModelFactory(
            getCharactersUseCase,
            getSingleCharacterUseCase
        )
    }
}