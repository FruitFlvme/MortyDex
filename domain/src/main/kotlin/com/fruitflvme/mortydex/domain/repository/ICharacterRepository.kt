package com.fruitflvme.mortydex.domain.repository

import androidx.paging.PagingData
import com.fruitflvme.mortydex.domain.model.Character
import com.fruitflvme.mortydex.domain.model.CharacterQuery
import kotlinx.coroutines.flow.Flow

interface ICharacterRepository {
    fun getCharacters(query: CharacterQuery): Flow<PagingData<Character>>
    suspend fun getCharacterById(id: Int): Character
}