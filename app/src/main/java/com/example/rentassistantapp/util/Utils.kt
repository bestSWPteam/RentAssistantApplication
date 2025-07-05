package com.example.rentassistantapp.util

import com.example.rentassistantapp.data.model.PaymentStatusResponse
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import kotlinx.coroutines.delay
import retrofit2.Response

fun buildTelegramAuthUrl(
    botId: String,
    redirect: String,
    origin: String? = null
): String {
    val params = mutableListOf<String>().apply {
        add("bot_id=${URLEncoder.encode(botId, StandardCharsets.UTF_8.name())}")
        add("redirect_url=${URLEncoder.encode(redirect, StandardCharsets.UTF_8.name())}")
        origin?.let {
            add("origin=${URLEncoder.encode(it, StandardCharsets.UTF_8.name())}")
        }
        add("request_access=write")
    }
    return "https://oauth.telegram.org/auth?" + params.joinToString("&")
}

fun getFullDescription(planName: String): String = when (planName) {
    "Лайт" -> "Подходит для повседневных личных задач: записи к врачу, поиск подарков, бронирование билетов, напоминания, оформление заказов, помощь в планировании и мелких делах."
    "Бизнес" -> "Подходит для бизнес-коммуникаций, аналитических запросов, организации встреч, ведения переписки, составления документов и других рабочих задач."
    "Экстра" -> "Подходит для комплексной поддержки: бизнес-коммуникации, организация встреч, аналитика, а также личные дела — записи, напоминания, бронирования, заказы и повседневная рутина."
    else -> ""
}

suspend fun pollUntilPaid(
    getStatus: suspend (String) -> Response<PaymentStatusResponse>,
    paymentId: String,
    checkIntervalMs: Long = 3000L
): String {
    var status: String
    do {
        delay(checkIntervalMs)
        status = getStatus(paymentId).toString()
    } while (status != "PAID")
    return status
}

fun <T> filterTasks(
    tasks: List<T>,
    onlyPending: Boolean,
    isDone: (T) -> Boolean
): List<T> = tasks.filter { task ->
    if (onlyPending) !isDone(task) else isDone(task)
}
