package com.example.rentassistantapp.data.model

data class PaymentRequest(
    val user_telegram_id: String,
    val plan: String,
    val plan_hrs: String,
    val amount: Int,
    val payment_txn_id: String
)