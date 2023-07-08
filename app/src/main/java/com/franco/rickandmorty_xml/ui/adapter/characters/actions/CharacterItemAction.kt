package com.franco.rickandmorty_xml.ui.adapter.characters.actions

import com.franco.rickandmorty_xml.CharacterListQuery

sealed interface CharacterItemAction {
    class ClickSelect(val selectCharacter: CharacterListQuery.Result) : CharacterItemAction
}