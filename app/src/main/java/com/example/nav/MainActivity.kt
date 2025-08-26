package com.example.nav

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.nav.navigation.NavigationHost
import com.example.nav.ui.CharacterViewModel // Import CharacterViewModel
import com.example.nav.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
    // Instanciar el CharacterViewModel usando el delegado by viewModels()
    // Esto asegura que el ViewModel persista durante los cambios de configuración
    private val characterViewModel: CharacterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavigationHost(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        // Pasar la instancia del ViewModel al grafo de navegación
                        characterViewModel = characterViewModel
                    )
                }
            }
        }
    }
}