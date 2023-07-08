package com.franco.rickandmorty_xml.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.franco.rickandmorty_xml.domain.usecase.GetCharactersUseCase
import com.franco.rickandmorty_xml.domain.usecase.GetSingleCharacterUseCase

class MainViewModelFactory(
    private val getCharactersUseCase: GetCharactersUseCase,
    private val getSingleCharacterUseCase: GetSingleCharacterUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(
            getCharactersUseCase,
            getSingleCharacterUseCase
        ) as T
    }
}