package com.example.nav.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.nav.data.UserInfo
import com.example.nav.data.UsersList
import com.example.nav.network.RestAPI
import com.example.nav.ui.components.UserItem

@Composable
fun ListScreen(api: RestAPI = RestAPI(), onNavigate: (UserInfo) -> Unit) {
    val context = LocalContext.current
    var users by remember { mutableStateOf(listOf<UserInfo>()) }

    LaunchedEffect(key1 = null) {
        users = api.getUsers().results
    }

    LazyColumn(horizontalAlignment = Alignment.CenterHorizontally) {
        stickyHeader {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.inversePrimary)
                    .padding(16.dp)
            ) {
                Text("Lista de usuario: ${users.size}", style = MaterialTheme.typography.titleLarge)
            }
        }
        if (users.isEmpty()) {
            item {
                Text("Loading users...")
            }
        } else {
            items(items = users) { usr ->
                UserItem(
                    modifier = Modifier.clickable(
                        true,
                        onClick = {
                            Toast.makeText(context, "Navigate", Toast.LENGTH_SHORT).show()
                            onNavigate(usr)
                        }
                    ), usr = usr)
            }
        }
    }
}