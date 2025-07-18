package com.fruitflvme.mortydex.domain.di

import com.fruitflvme.mortydex.domain.usecase.GetCharacterByIdUseCase
import com.fruitflvme.mortydex.domain.usecase.GetCharactersUseCase
import org.koin.dsl.module

object DomainModule {
    val module = module {
        single { GetCharactersUseCase(get()) }
        single { GetCharacterByIdUseCase(get()) }
    }
}