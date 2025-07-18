package com.fruitflvme.mortydex.data.di

import com.fruitflvme.mortydex.data.repository.CharacterRepositoryImpl
import com.fruitflvme.mortydex.domain.repository.ICharacterRepository
import org.koin.dsl.module

object RepositoryModule {
    val module = module {
        single<ICharacterRepository> { CharacterRepositoryImpl(get(), get()) }
    }
}