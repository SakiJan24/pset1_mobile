package com.example.nav.network

import com.example.nav.data.UsersList
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class RestAPI {
    //objeto que sabe cómo hacer solicitudes por internet
    //OkHttp es el motor para hacer las conexiones
    private val client = HttpClient(OkHttp) {
        defaultRequest {
            // URL base más amplia que sirve para si en el futuro
            // queremos acceder a otras partes y no solo character
            url("https://rickandmortyapi.com/api/")
        }

        install(Logging) {
            logger = Logger.SIMPLE
            level = LogLevel.BODY
        }

        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                isLenient = true
            })
        }
    }

    suspend fun getUsers(): UsersList {
        // aqui ahora se especifica el endpoint "character"
        return client.get("character").body()
    }
}