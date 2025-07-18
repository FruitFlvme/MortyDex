package com.fruitflvme.mortydex.presentation.state

import com.fruitflvme.mortydex.domain.model.Character

sealed interface CharacterDetailsUiState {
    data object Loading : CharacterDetailsUiState
    data class Success(val character: Character) : CharacterDetailsUiState
    data class Error(val message: String) : CharacterDetailsUiState
}