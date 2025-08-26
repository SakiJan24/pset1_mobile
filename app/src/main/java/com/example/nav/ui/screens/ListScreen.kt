package com.example.nav.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.nav.R
import com.example.nav.data.CharacterInfo
import com.example.nav.ui.CharacterViewModel
import com.example.nav.ui.components.UserItem

@Composable
fun ListScreen(
    modifier: Modifier = Modifier,
    characterViewModel: CharacterViewModel = viewModel(),
    onNavigate: (CharacterInfo) -> Unit
) {
    val context = LocalContext.current

    val characters by characterViewModel.characters.collectAsState()
    val totalCharacters by characterViewModel.totalCharacters.collectAsState()
    val isLoading by characterViewModel.isLoading.collectAsState()
    val errorMessage by characterViewModel.errorMessage.collectAsState()

    // Mostrar un indicador de carga si los datos no están listos
    if (isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(modifier = Modifier.padding(16.dp))
        }
    } else if (errorMessage != null) {
        // Mostrar un mensaje de error si la carga falla
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(id = R.string.list_screen_error_message, errorMessage!!),
                color = MaterialTheme.colorScheme.error
            )
        }
    } else if (characters.isEmpty()) {
        // Mostrar un mensaje si la lista está vacía y no hay errores
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = stringResource(id = R.string.list_screen_empty_message))
        }
    } else {
        // Muestra la lista si los datos están disponibles
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            stickyHeader {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.inversePrimary)
                        .padding(16.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.list_screen_header_characters, totalCharacters),
                        style = MaterialTheme.typography.titleLarge
                    )
                }
            }
            items(items = characters) { character ->
                val navigatingToastMessage = stringResource(id = R.string.list_screen_toast_navigating)
                UserItem(
                    modifier = Modifier.clickable(
                        enabled = true,
                        onClick = {
                            Toast.makeText(context, navigatingToastMessage, Toast.LENGTH_SHORT).show()
                            onNavigate(character)
                        }
                    ),
                    usr = character,
                    onUserClick = onNavigate // This might be redundant if UserItem doesn't use it
                                             // when a clickable modifier is already applied like this.
                                             // However, keeping it for now to minimize changes beyond the fix.
                )
            }
        }
    }
}
