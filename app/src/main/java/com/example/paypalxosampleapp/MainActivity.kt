package com.example.paypalxosampleapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import com.example.paypalxosampleapp.api.*
import androidx.lifecycle.lifecycleScope
import com.paypal.checkout.PayPalCheckout
import com.paypal.checkout.approve.OnApprove
import com.paypal.checkout.cancel.OnCancel
import com.paypal.checkout.createorder.CreateOrder
import com.paypal.checkout.error.OnError
import com.paypal.checkout.paymentbutton.PaymentButtonContainer
import com.paypal.checkout.shipping.OnShippingChange

class MainActivity : AppCompatActivity() {

    var demoAPI = RetrofitClient.getInstance().create(DemoAPI::class.java)
    lateinit var paymentButtonContainer: PaymentButtonContainer

    var orderID: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        paymentButtonContainer = findViewById(R.id.payment_button_container) as PaymentButtonContainer

        fetchAccessToken()
        fetchOrderID()

        configurePayPalCheckout()
    }

    fun configurePayPalCheckout() {
        paymentButtonContainer.setup(
            createOrder = CreateOrder { createOrderActions ->
                createOrderActions.set(orderID!!)
            },
            onApprove = OnApprove {
                // Handle result of order approval (authorize or capture)
            },
            onCancel = OnCancel {
                // Handle cancel case
            },
            onError = OnError { errorInfo ->
                // Handle error case
            },
            onShippingChange = OnShippingChange { shippingChangeData, shippingChangeActions ->
                // Handle user shipping address & method selection change
            }
        )
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
                orderID = order.id
                paymentButtonContainer.visibility = View.VISIBLE
                Log.e("Fetched orderID: ", order.id)
            } catch (Ex: Exception) {
                Log.e("Error", Ex.localizedMessage)
            }
        }
    }
}