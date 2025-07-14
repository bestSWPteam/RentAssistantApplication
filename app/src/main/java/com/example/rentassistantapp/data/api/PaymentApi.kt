package com.example.rentassistantapp.data.api

import com.example.rentassistantapp.data.model.PaymentCreateResponse
import com.example.rentassistantapp.data.model.PaymentRequest
import com.example.rentassistantapp.data.model.PaymentStatusResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface PaymentApi {
    @POST("payments/create")
    suspend fun createPayment(
        @Header("X-Auth-Token") jwt: String,
        @Body body: PaymentRequest
    ): Response<PaymentCreateResponse>

    @GET("payments/status/{paymentId}")
    suspend fun getPaymentStatus(
        @Query("paymentId") paymentId: String
    ): Response<PaymentStatusResponse>
}