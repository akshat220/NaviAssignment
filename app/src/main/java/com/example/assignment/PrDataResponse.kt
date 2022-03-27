package com.example.assignment

import com.google.gson.annotations.SerializedName

data class PrDataResponse(
    val id: Long?,
    val user: UserData?,
    @SerializedName("created_at") val createdAt: String?,
    @SerializedName("closed_at") val closedAt: String?
)

data class UserData(
    val login: String?,
    @SerializedName("avatar_url") val avatarUrl: String?
)
