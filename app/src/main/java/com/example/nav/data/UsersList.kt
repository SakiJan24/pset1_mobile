package com.example.nav.data

import kotlinx.serialization.Serializable

@Serializable
data class UsersList(
    val info: Info,
    val results: List<UserInfo>
)


@Serializable
data class Info(

    val count: Int,
    val pages: Int,
    val next: String?,
    val prev: String?
)