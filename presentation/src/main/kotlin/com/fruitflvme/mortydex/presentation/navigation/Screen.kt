package com.fruitflvme.mortydex.presentation.navigation

sealed class Screen(val route: String) {
    data object Characters : Screen("characters")
    data object CharacterDetails : Screen("character_details")
}
