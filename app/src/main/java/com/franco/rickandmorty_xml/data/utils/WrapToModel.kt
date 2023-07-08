package com.franco.rickandmorty_xml.data.utils

import com.franco.rickandmorty_xml.CharacterInfoQuery
import com.franco.rickandmorty_xml.domain.model.Episode
import com.franco.rickandmorty_xml.domain.model.InfoCharacter
import com.franco.rickandmorty_xml.domain.model.Origin

fun CharacterInfoQuery.Character.toModel(): InfoCharacter {
    return InfoCharacter(
        name = name,
        gender = gender,
        origin = Origin(
            name= origin?.name,
            type = origin?.type,
            dimension = origin?.dimension
        ),
        episode = episode.mapNotNull { episodes ->
            Episode(
                id = episodes?.id,
                name = episodes?.name
            ) },
        image = image,
        status = status
    )
}