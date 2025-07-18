package com.fruitflvme.mortydex.data.mapper

import com.fruitflvme.mortydex.data.database.entity.CharacterEntity
import com.fruitflvme.mortydex.data.network.dto.CharacterDto
import com.fruitflvme.mortydex.domain.model.Character

fun CharacterDto.toDomain(): Character {
    return Character(
        id = id,
        name = name,
        status = status,
        species = species,
        type = type,
        gender = gender,
        origin = origin.name,
        location = location.name,
        image = image,
        episodeCount = episode.size
    )
}

fun CharacterDto.toEntity(): CharacterEntity {
    return CharacterEntity(
        id = id,
        name = name,
        status = status,
        species = species,
        type = type,
        gender = gender,
        origin = origin.name,
        location = location.name,
        image = image,
        episodeCount = episode.size
    )
}

fun CharacterEntity.toDomain(): Character = Character(
    id = id,
    name = name,
    status = status,
    species = species,
    type = type,
    gender = gender,
    origin = origin,
    location = location,
    image = image,
    episodeCount = episodeCount
)
