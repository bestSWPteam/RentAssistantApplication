package com.example.rentassistantapp

import com.example.rentassistantapp.data.model.PaymentStatusResponse
import com.example.rentassistantapp.util.buildTelegramAuthUrl
import com.example.rentassistantapp.util.getFullDescription
import com.example.rentassistantapp.util.pollUntilPaid
import com.example.rentassistantapp.util.filterTasks
import kotlinx.coroutines.runBlocking
import org.junit.Assert.fail
import retrofit2.Response
import org.junit.Assert.*
import org.junit.Test
import java.net.URI

data class Task(val id: Int, val isDone: Boolean)

class AppUnitTests {

    @Test
    fun `Auth URL builder contains all required query params`() {
        val url = buildTelegramAuthUrl(
            botId = "7497767876",
            redirect = "myapp://auth-callback",
            origin = "https://rentassistant.ru/"
        )
        val uri = URI(url)
        val query = uri.query.split("&").associate {
            val (k, v) = it.split("=")
            k to v
        }
        assertEquals("7497767876", query["bot_id"])
        assertEquals("myapp://auth-callback", query["redirect_url"])
        assertEquals("https://rentassistant.ru/", query["origin"])
        assertEquals("write", query["request_access"])
    }

    @Test
    fun `getFullDescription returns correct non-empty descriptions`() {
        assertTrue(getFullDescription("Лайт").contains("личных задач"))
        assertTrue(getFullDescription("Бизнес").contains("бизнес-коммуникаций"))
        assertTrue(getFullDescription("Экстра").contains("комплексной поддержки"))
    }

    @Test
    fun `getFullDescription returns empty for unknown plan`() {
        assertEquals("", getFullDescription("UnknownPlan"))
    }

    @Test
    fun `pollUntilPaid stops when status becomes PAID`() = runBlocking {
        var callCount = 0
        val fakeApi: suspend (String) -> Response<PaymentStatusResponse> = { _ ->
            if (callCount++ == 0) {
                Response.success(PaymentStatusResponse("PENDING"))
            } else {
                Response.success(PaymentStatusResponse("PAID"))
            }
        }

        val result = pollUntilPaid(fakeApi, paymentId = "abc", checkIntervalMs = 1)
        assertEquals("PAID", result)
    }

    @Test
    fun `filterTasks returns only pending when flag true`() {
        val tasks = listOf(Task(1, false), Task(2, true), Task(3, false))
        val pending = filterTasks(tasks, onlyPending = true) { it.isDone }
        assertTrue(pending.all { !it.isDone })
        assertEquals(2, pending.size)
    }


    @Test
    fun `Auth URL builder uses correct scheme`() {
        val url = buildTelegramAuthUrl("bot", "myapp://callback", "origin")
        assertTrue(url.startsWith("https://t.me/"))
    }

    @Test
    fun `getFullDescription handles lowercase plan`() {
        val result = getFullDescription("лайт")
        assertEquals("", result)
    }

    @Test
    fun `pollUntilPaid returns PENDING if never paid`() = runBlocking {
        val fakeApi: suspend (String) -> Response<PaymentStatusResponse> = { _ ->
            Response.success(PaymentStatusResponse("PENDING"))
        }
        val result = pollUntilPaid(fakeApi, "123", checkIntervalMs = 1)
        assertEquals("PENDING", result)
    }

    @Test
    fun `filterTasks returns all when onlyPending is false`() {
        val tasks = listOf(Task(1, false), Task(2, true))
        val result = filterTasks(tasks, onlyPending = false) { it.isDone }
        assertEquals(2, result.size)
    }

    @Test
    fun `buildTelegramAuthUrl returns valid URI`() {
        val url = buildTelegramAuthUrl("bot", "myapp://callback", "origin")
        try {
            URI(url)
        } catch (e: Exception) {
            fail("URL is not a valid URI: ${e.message}")
        }
    }



}
