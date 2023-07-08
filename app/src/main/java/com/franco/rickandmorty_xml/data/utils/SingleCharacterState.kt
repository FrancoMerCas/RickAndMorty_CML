package com.franco.rickandmorty_xml.data.utils

import com.franco.rickandmorty_xml.domain.model.InfoCharacter

sealed class SingleCharacterState {
    object Loading : SingleCharacterState()
    data class Success(val character: InfoCharacter) : SingleCharacterState()
    data class Error(val exception: Exception) : SingleCharacterState()
}
