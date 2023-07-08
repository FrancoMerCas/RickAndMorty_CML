package com.franco.rickandmorty_xml.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.apollographql.apollo3.api.ApolloResponse
import com.franco.rickandmorty_xml.CharacterListQuery
import com.franco.rickandmorty_xml.data.utils.SingleCharacterState
import com.franco.rickandmorty_xml.domain.model.CharactersItemModel
import com.franco.rickandmorty_xml.domain.model.InfoCharacter
import com.franco.rickandmorty_xml.domain.usecase.GetCharactersUseCase
import com.franco.rickandmorty_xml.domain.usecase.GetSingleCharacterUseCase
import com.franco.rickandmorty_xml.ui.paging.CharactersPaging
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase,
    private val getSingleCharacterUseCase: GetSingleCharacterUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(CharactersState())
    val state = _state.asStateFlow()

    private val _list = MutableLiveData<ApolloResponse<CharacterListQuery.Data>>()
    val list : LiveData<ApolloResponse<CharacterListQuery.Data>> = _list

    private val _singleCharacterState = MutableStateFlow<SingleCharacterState>(SingleCharacterState.Loading)
    val singleCharacterState = _singleCharacterState.asStateFlow()

    private val _currentCharacter = MutableLiveData<CharacterListQuery.Result>()

    fun getSingleCharacter() {
        viewModelScope.launch {
            getSingleCharacterUseCase.invoke(_currentCharacter.value?.id.toString()).collect { state ->
                _singleCharacterState.value = state
            }
        }
    }

    val charactersList = Pager(
        PagingConfig(20)
    ) {
        CharactersPaging(
            getCharactersUseCase
        )
    }.flow

    data class CharactersState(
        val characterList: List<CharactersItemModel>? = emptyList(),
        val isLoading: Boolean = false,
        val infoCharacter: InfoCharacter? = null
    )

    fun setCurrentCharacter(character: CharacterListQuery.Result) {
        _currentCharacter.value = character
    }
}