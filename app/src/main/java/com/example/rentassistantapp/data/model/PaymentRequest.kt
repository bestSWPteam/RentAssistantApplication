package com.example.rentassistantapp.data.model

data class PaymentRequest(
    val user_telegram_id: String?,
    val plan: String,
    val plan_hrs: Int,
    val amount: Int,
    val payment_txn_id: String
)