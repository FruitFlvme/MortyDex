package com.fruitflvme.mortydex.presentation.di

import com.fruitflvme.mortydex.presentation.viewmodel.CharacterDetailsViewModel
import com.fruitflvme.mortydex.presentation.viewmodel.CharactersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object PresentationModule {
    val module = module {
        viewModel { CharactersViewModel(get()) }
        viewModel { (characterId: Int) -> CharacterDetailsViewModel(get(), characterId) }
    }
}