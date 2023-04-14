package com.example.paypalxosampleapp

import com.google.gson.annotations.SerializedName

data class AccessToken(
    @SerializedName("access_token")
    val value: String
)

