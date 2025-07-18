package com.fruitflvme.mortydex.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.fruitflvme.mortydex.presentation.ui.CharacterDetailsScreen
import com.fruitflvme.mortydex.presentation.ui.CharactersScreen
import com.fruitflvme.mortydex.presentation.viewmodel.CharacterDetailsViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun AppNavGraph(
    navController: NavHostController,
    paddingValues: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Characters.route,
        modifier = Modifier.padding(paddingValues)
    ) {
        composable(Screen.Characters.route) {
            CharactersScreen(
                onCharacterClick = { characterId ->
                    navController.navigate("${Screen.CharacterDetails.route}/$characterId")
                }
            )
        }

        composable(
            route = "${Screen.CharacterDetails.route}/{characterId}",
            arguments = listOf(navArgument("characterId") { type = NavType.IntType })
        ) { backStackEntry ->

            val characterId = backStackEntry.arguments?.getInt("characterId") ?: return@composable

            val viewModel: CharacterDetailsViewModel =
                koinViewModel(parameters = { parametersOf(characterId) })

            CharacterDetailsScreen(viewModel)
        }
    }
}

