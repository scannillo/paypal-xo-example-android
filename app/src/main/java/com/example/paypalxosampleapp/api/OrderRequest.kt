package com.example.paypalxosampleapp.api

import com.google.gson.annotations.SerializedName

data class OrderRequest(
    val intent: String?,
    @SerializedName("purchase_units")
    val purchaseUnit: List<PurchaseUnit>?
)

data class PurchaseUnit(
    val amount: Amount
)

data class Amount(
    @SerializedName("currency_code")
    val currencyCode: String?,
    val value: String?
)