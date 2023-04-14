package com.example.paypalxosampleapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.paypalxosampleapp.api.*
import androidx.lifecycle.lifecycleScope

class MainActivity : AppCompatActivity() {

    var demoAPI = RetrofitClient.getInstance().create(DemoAPI::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fetchAccessToken()
        fetchOrderID()
    }

    fun fetchAccessToken() {
        lifecycleScope.launchWhenCreated {
            try {
                val accessToken = demoAPI.fetchAccessToken()
                Log.e("Fetched access token: ", accessToken.value)
            } catch (Ex: Exception) {
                Log.e("Error", Ex.localizedMessage)
            }
        }
    }

    fun fetchOrderID() {
        val orderRequest = OrderRequest(
            intent = "AUTHORIZE",
            purchaseUnit = listOf(
                PurchaseUnit(
                    Amount(
                        currencyCode = "USD",
                        value = "10.99"
                    )
                )
            )
        )

        lifecycleScope.launchWhenCreated {
            try {
                val order = demoAPI.createOrder(orderRequest)
                Log.e("Fetched orderID: ", order.id)
            } catch (Ex: Exception) {
                Log.e("Error", Ex.localizedMessage)
            }
        }
    }
}