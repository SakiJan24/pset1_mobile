package com.example.nav.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.nav.R // Import R class

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onNavigate: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            Icons.Filled.Home,
            contentDescription = null,
            Modifier.size(64.dp),
            tint = MaterialTheme.colorScheme.primary
        )
        Text(
            stringResource(id = R.string.home_screen_title),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(Modifier.height(80.dp))
        Button(onClick = { onNavigate() }) {
            Text(stringResource(id = R.string.go_to_list_button))
        }
    }
}
