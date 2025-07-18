package com.fruitflvme.mortydex.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fruitflvme.mortydex.domain.usecase.GetCharacterByIdUseCase
import com.fruitflvme.mortydex.presentation.state.CharacterDetailsUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CharacterDetailsViewModel(
    private val getCharacterByIdUseCase: GetCharacterByIdUseCase,
    private val characterId: Int
) : ViewModel() {

    private val _uiState =
        MutableStateFlow<CharacterDetailsUiState>(CharacterDetailsUiState.Loading)
    val uiState: StateFlow<CharacterDetailsUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            try {
                val character = getCharacterByIdUseCase(characterId)
                _uiState.value = CharacterDetailsUiState.Success(character)
            } catch (e: Exception) {
                _uiState.value = CharacterDetailsUiState.Error(e.message ?: "Unknown error")
            }
        }
    }
}