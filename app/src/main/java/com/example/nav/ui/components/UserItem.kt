package com.example.nav.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.nav.data.CharacterInfo

@Composable
fun UserItem(
    modifier: Modifier = Modifier,
    // El parámetro espera un objeto CharacterInfo
    usr: CharacterInfo,
    onUserClick: (CharacterInfo) -> Unit
) {
    ListItem(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onUserClick(usr) },
        headlineContent = {
            Text(usr.name, style = MaterialTheme.typography.bodyLarge)
        }, supportingContent = {
            Column {
                //
                Text(usr.status, style = MaterialTheme.typography.bodyMedium)
            }
        }, trailingContent = {
            Icon(Icons.Default.ArrowForward, contentDescription = null)
        }, leadingContent = {
            AsyncImage(
                usr.image,
                contentDescription = null,
                modifier = Modifier
                    .clip(CircleShape)
                    .background(
                        MaterialTheme.colorScheme.primary
                    )
                    .size(64.dp)
            )
        }
    )
}
