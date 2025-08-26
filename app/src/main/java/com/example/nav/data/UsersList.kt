package com.example.nav.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UsersList(
    @SerialName("info") val info: PageInfo,
    @SerialName("results") val results: List<CharacterInfo>
)

@Serializable
data class PageInfo(
    @SerialName("count") val count: Int,
    @SerialName("pages") val pages: Int,
    @SerialName("next") val next: String?,
    @SerialName("prev") val prev: String?
)

@Serializable
data class CharacterInfo(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("status") val status: String,
    @SerialName("species") val species: String,
    @SerialName("type") val type: String,
    @SerialName("gender") val gender: String,
    @SerialName("origin") val origin: OriginInfo,
    @SerialName("location") val location: LocationInfo,
    @SerialName("image") val image: String,
    @SerialName("episode") val episode: List<String>,
    @SerialName("url") val url: String,
    @SerialName("created") val created: String
)

@Serializable
data class OriginInfo(
    @SerialName("name") val name: String,
    @SerialName("url") val url: String
)

@Serializable
data class LocationInfo(
    @SerialName("name") val name: String,
    @SerialName("url") val url: String
)
