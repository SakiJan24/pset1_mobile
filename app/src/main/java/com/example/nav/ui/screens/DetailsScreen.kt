package com.example.nav.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.nav.data.UserInfo

@Composable
fun DetailsScreen(usr: UserInfo) {
    Column(modifier = Modifier.padding(34.dp)) {
        AsyncImage(
            usr.image,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
        )
        Column(
            modifier = Modifier
                .padding(horizontal = 20.dp)
        ) {
            Spacer(Modifier.height(60.dp))
            Text(
                "Name: ${usr.name}",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                "Gender: ${usr.gender}",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.secondary
            )
            Text(
                "Location: ${usr.location.name}",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.secondary
            )
            Spacer(Modifier.height(20.dp))
            Text(
                "Origin: ${usr.origin.name}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.tertiary
            )
            Text(
                "Species: ${usr.species}",
                style = MaterialTheme.typography.bodyMedium,
            )
            Spacer(Modifier.height(20.dp))
            Text(
                "Status: ${usr.status}",
                style = MaterialTheme.typography.bodySmall,
            )
        }
    }
}