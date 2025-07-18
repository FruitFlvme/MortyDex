package com.fruitflvme.mortydex.domain.usecase

import androidx.paging.PagingData
import com.fruitflvme.mortydex.domain.model.Character
import com.fruitflvme.mortydex.domain.model.CharacterQuery
import com.fruitflvme.mortydex.domain.repository.ICharacterRepository
import kotlinx.coroutines.flow.Flow

class GetCharactersUseCase(
    private val repository: ICharacterRepository
) {
    operator fun invoke(query: CharacterQuery): Flow<PagingData<Character>> =
        repository.getCharacters(query)
}
