package com.example.paypalxosampleapp.api

import com.google.gson.JsonObject
import retrofit2.http.Body
import retrofit2.http.POST

interface DemoAPI {

    @POST("/orders")
    suspend fun createOrder(@Body orderRequest: OrderRequest?): Order

    @POST("/access_tokens")
    suspend fun fetchAccessToken(): AccessToken
}
