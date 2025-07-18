package com.fruitflvme.mortydex.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fruitflvme.mortydex.domain.model.CharacterQuery
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterModalSheet(
    sheetState: SheetState,
    filters: CharacterQuery,
    onApplyFilters: (CharacterQuery) -> Unit,
    onDismissRequest: () -> Unit
) {
    val scope = rememberCoroutineScope()

    var status by remember { mutableStateOf(filters.status) }
    var species by remember { mutableStateOf(filters.species) }
    var gender by remember { mutableStateOf(filters.gender) }

    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = sheetState
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text("Filteres", style = MaterialTheme.typography.titleLarge)

            Spacer(modifier = Modifier.height(8.dp))

            FilterGroup("Status", listOf(null, "Alive", "Dead", "unknown"), status) {
                status = it
            }

            FilterGroup("Kind", listOf(null, "Human", "Alien", "Robot"), species) {
                species = it
            }

            FilterGroup("Gender", listOf(null, "Male", "Female", "Genderless", "unknown"), gender) {
                gender = it
            }

            Button(
                onClick = {
                    onApplyFilters(
                        CharacterQuery(
                            name = filters.name,
                            status = status,
                            species = species,
                            gender = gender
                        )
                    )
                    scope.launch {
                        sheetState.hide()
                        onDismissRequest()
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Применить")
            }
        }
    }
}