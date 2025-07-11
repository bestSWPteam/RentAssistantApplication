package com.example.rentassistantapp

import android.net.Uri
import com.example.rentassistantapp.util.buildTelegramAuthUrl
import com.example.rentassistantapp.util.getFullDescription
import com.example.rentassistantapp.util.pollUntilPaid
import com.example.rentassistantapp.util.filterTasks
import kotlinx.coroutines.runBlocking
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
        val responses = listOf("PENDING", "PAID").iterator()
        val fakeApi: suspend (String) -> String = { responses.next() }
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
}
