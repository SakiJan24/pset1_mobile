package com.example.nav.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.nav.data.CharacterInfo
import com.example.nav.R

@Composable
fun DetailsScreen(usr: CharacterInfo) {
    val context = LocalContext.current

    Column(
        modifier = Modifier.padding(34.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            usr.image,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .clickable {
                    val intent = Intent(
                        Intent.ACTION_DIAL,
                        Uri.parse("tel:${usr.id}")
                    )
                    context.startActivity(intent)
                }
        )
        Column(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(60.dp))
            Text(
                stringResource(id = R.string.details_label_name, usr.name),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                stringResource(id = R.string.details_label_gender, usr.gender),
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.secondary
            )
            Text(
                stringResource(id = R.string.details_label_location, usr.location.name),
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.secondary
            )
            Spacer(Modifier.height(20.dp))
            Text(
                stringResource(id = R.string.details_label_origin, usr.origin.name),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.tertiary
            )
            Text(
                stringResource(id = R.string.details_label_species, usr.species),
                style = MaterialTheme.typography.bodyMedium,
            )
            Spacer(Modifier.height(20.dp))
            Text(
                stringResource(id = R.string.details_label_status, usr.status),
                style = MaterialTheme.typography.bodySmall,
            )
        }
    }
}
