package com.example.assignment.model

import com.google.gson.annotations.SerializedName

data class PrDataResponse(
    val title: String?,
    val user: UserData?,
    @SerializedName("created_at") val createdAt: String?,
    @SerializedName("closed_at") val closedAt: String?
)

data class UserData(
    val login: String?,
    @SerializedName("avatar_url") val avatarUrl: String?
)
