package com.fruitflvme.mortydex.domain.usecase

import com.fruitflvme.mortydex.domain.model.Character
import com.fruitflvme.mortydex.domain.repository.ICharacterRepository

class GetCharacterByIdUseCase(
    private val repository: ICharacterRepository
) {
    suspend operator fun invoke(id: Int): Character {
        return repository.getCharacterById(id)
    }
}