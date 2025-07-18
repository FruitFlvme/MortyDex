@file:OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)

package com.fruitflvme.mortydex.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.fruitflvme.mortydex.domain.model.Character
import com.fruitflvme.mortydex.domain.model.CharacterQuery
import com.fruitflvme.mortydex.domain.usecase.GetCharactersUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn

class CharactersViewModel(
    private val getCharactersUseCase: GetCharactersUseCase
) : ViewModel() {

    private val _filters = MutableStateFlow(CharacterQuery())
    val filters: StateFlow<CharacterQuery> = _filters.asStateFlow()

    var characters: StateFlow<PagingData<Character>> = _filters
        .debounce(300)
        .flatMapLatest { filters ->
            getCharactersUseCase(filters)
                .cachedIn(viewModelScope)
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5_000),
            PagingData.empty()
        )

    fun setFilters(filters: CharacterQuery) {
        _filters.value = filters
    }
}