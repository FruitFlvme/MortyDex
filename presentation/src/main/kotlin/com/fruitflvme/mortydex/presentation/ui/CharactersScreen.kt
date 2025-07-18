@file:OptIn(ExperimentalMaterial3Api::class)

package com.fruitflvme.mortydex.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.fruitflvme.mortydex.presentation.viewmodel.CharactersViewModel
import org.koin.androidx.compose.koinViewModel


@Composable
fun CharactersScreen(
    viewModel: CharactersViewModel = koinViewModel(),
    onCharacterClick: (Int) -> Unit
) {
    val modalSheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var isSheetOpen by remember { mutableStateOf(false) }

    val characters = viewModel.characters.collectAsLazyPagingItems()
    val state = characters.loadState
    val isLoading = state.refresh is LoadState.Loading
    val isError = state.refresh is LoadState.Error

    val filters = viewModel.filters.collectAsState().value
    val isAnyFilterApplied = filters.run {
        (name?.isNotBlank() == true) ||
                (status?.isNotBlank() == true) ||
                (species?.isNotBlank() == true) ||
                (gender?.isNotBlank() == true)
    }
    val isEmpty = !isLoading && !isError && characters.itemCount == 0 && isAnyFilterApplied

    val columns = 2

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            var search by remember { mutableStateOf("") }

            OutlinedTextField(
                value = search,
                onValueChange = {
                    search = it
                    viewModel.setFilters(viewModel.filters.value.copy(name = search))
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                placeholder = { Text("Name character") },
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(onSearch = {})
            )

            when {
                isLoading -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }

                isError -> {
                    val e = (state.refresh as LoadState.Error).error
                    ErrorState(
                        message = "Ошибка загрузки: ${e.message}",
                        onRetry = { characters.retry() }
                    )
                }

                isEmpty -> {
                    EmptyState(message = "Ничего не найдено. Попробуйте изменить фильтры.")
                }

                else -> {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(columns),
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(
                            count = characters.itemCount,
                            key = { index -> characters[index]?.id ?: index }
                        ) { index ->
                            characters[index]?.let { character ->
                                CharacterItem(
                                    character = character,
                                    onClick = { onCharacterClick(character.id) })
                            }
                        }
                        if (state.append is LoadState.Loading) {
                            item(span = { GridItemSpan(columns) }) {
                                CircularProgressIndicator(modifier = Modifier.padding(16.dp))
                            }
                        }
                    }
                }
            }
        }

        if (!isSheetOpen) {
            FloatingActionButton(
                onClick = { isSheetOpen = true },
                containerColor = MaterialTheme.colorScheme.tertiary,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "Фильтры"
                )
            }
        }
    }

    // Modal Bottom Sheet
    if (isSheetOpen) {
        FilterModalSheet(
            sheetState = modalSheetState,
            filters = viewModel.filters.collectAsState().value,
            onApplyFilters = { viewModel.setFilters(it) },
            onDismissRequest = { isSheetOpen = false }
        )
    }
}









