package com.example.nav.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nav.data.CharacterInfo
import com.example.nav.network.RestAPI
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.IOException

// aqui se define el viewmodel que gestiona los datos de los personajes.
//Un ViewModel es una clase especial que se usa para guardar y manejar
//datos de la app mientras la pantalla (la vista) está viva.
class CharacterViewModel : ViewModel() {

    // aqui se instancia la clase RestAPI para realizar la llamada de red.
    private val apiService = RestAPI() //este es el archivo que busca la url de la api

    // "MutableStateFlow" privado para almacenar la lista de personajes.
    // lo "mutable" permite que el ViewModel actualice su valor.
    private val _characters = MutableStateFlow<List<CharacterInfo>>(emptyList())
    // Flujo de estado público e inmutable para exponer la lista de personajes a la UI.
    // La UI observará este flujo para reaccionar a los cambios.
    val characters: StateFlow<List<CharacterInfo>> = _characters.asStateFlow()

    // Flujo de estado mutable privado para almacenar el número total de personajes.
    private val _totalCharacters = MutableStateFlow(0)
    // Flujo de estado público e inmutable para exponer el número total de personajes.
    val totalCharacters: StateFlow<Int> = _totalCharacters.asStateFlow()

    // Flujo de estado mutable privado para el estado de carga (por ejemplo, mostrando un spinner).
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    // MutableStateFlow para para manejar errores.
    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()


    // init: se ejecuta cuando se crea una instancia del ViewModel.
    // Aquí es el llamado a la función para obtener los personajes.
    init {
        fetchCharacters()
    }

    // Función para obtener los personajes de la API.
    fun fetchCharacters() {
        // viewModelScope.launch inicia una corrutina en el ámbito del ViewModel.
        // Esto asegura que la corrutina se cancele si el ViewModel se destruye.
        viewModelScope.launch {
            _isLoading.value = true // Indicar que la carga ha comenzado
            _errorMessage.value = null // Limpiar errores previos
            try {
                // Llama al metodo getUsers() de nuestra instancia de RestAPI.
                // Este metodo es suspendido, por lo que debe llamarse desde una corrutina.
                val response = apiService.getUsers()
                // Actualiza el flujo _characters con la lista de personajes obtenida.
                _characters.value = response.results
                // Actualiza el flujo _totalCharacters con la cantidad total de la API (info.count).
                _totalCharacters.value = response.info.count
            } catch (e: IOException) {
                // Maneja errores de red (ej. sin conexión).
                _errorMessage.value = "Error de red: No se pudo conectar a la API."
                // Imprime el error en la consola para depuración.
                e.printStackTrace()
            } catch (e: Exception) {
                // Maneja otros errores que puedan ocurrir durante la obtención de datos.
                _errorMessage.value = "Error desconocido: ${e.message}"
                // Imprime el error en la consola para depuración.
                e.printStackTrace()
            } finally {
                _isLoading.value = false // Indicar que la carga ha terminado
            }
        }
    }
}
