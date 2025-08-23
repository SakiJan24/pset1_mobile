package com.example.nav.data

import kotlinx.serialization.Serializable




@Serializable
data class UserInfo (

    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: Origin,
    val location: Location,
    val image: String,
    val episode: Array<String>,
    val url: String,
    val created: String
) {

    @Serializable
    data class Origin(

        val name: String,
        val url: String
    )

    @Serializable
    data class Location (

        val name: String,
        val url: String
    )
}