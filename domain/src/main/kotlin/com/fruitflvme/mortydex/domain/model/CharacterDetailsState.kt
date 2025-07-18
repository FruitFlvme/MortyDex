package com.fruitflvme.mortydex.domain.model

data class CharacterDetailsState(
    val character: Character? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
