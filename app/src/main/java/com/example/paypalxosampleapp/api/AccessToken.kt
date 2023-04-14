package com.example.paypalxosampleapp.api

import com.google.gson.annotations.SerializedName

data class AccessToken(
    @SerializedName("access_token")
    val value: String
)

