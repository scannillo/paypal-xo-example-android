package com.example.paypalxosampleapp

import android.app.Application
import com.paypal.checkout.PayPalCheckout
import com.paypal.checkout.config.CheckoutConfig
import com.paypal.checkout.config.Environment

class PayPalSampleApp : Application() {

    override fun onCreate() {
        super.onCreate()

        val config = CheckoutConfig(
            application = this,
            clientId = "AUiHPkr1LO7TzZH0Q5_aE8aGNmTiXZh6kKErYFrtXNYSDv13FrN2NElXabVV4fNrZol7LAaVb1gJj9lr",
            environment = Environment.SANDBOX,
            returnUrl = "${BuildConfig.APPLICATION_ID}://paypalpay"
        )
        PayPalCheckout.setConfig(config)
    }
}