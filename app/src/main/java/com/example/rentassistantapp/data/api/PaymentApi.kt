package com.example.rentassistantapp.data.api


import com.example.rentassistantapp.data.model.PaymentCreateRequest
import com.example.rentassistantapp.data.model.PaymentCreateResponse
import com.example.rentassistantapp.data.model.PaymentStatusResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface PaymentApi {
    @POST("payments/create")
    suspend fun createPayment(
        @Body req: PaymentCreateRequest
    ): Response<PaymentCreateResponse>

    @GET("payments/status")
    suspend fun getPaymentStatus(
        @Query("paymentId") paymentId: String
    ): Response<PaymentStatusResponse>
}