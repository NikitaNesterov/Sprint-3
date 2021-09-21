package ru.sber.ga

import com.nhaarman.mockitokotlin2.mock
import io.mockk.*
import org.junit.Before
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoJUnitRunner
import ru.sber.ga.Scanner.getScanData
import java.util.Scanner
import kotlin.random.Random

/**
 * @author Nikita Nesterov
 */

class ScannerTest {

    @BeforeEach
    fun setUp() {
        mockkObject(Scanner)
        mockkObject(Random)
    }

    @Test
    fun `getScanData should proceed with Random less then 10000`() {

        val data = Random.nextBytes(100)
        every { ru.sber.ga.Scanner.getScanData() } returns data
        every { Random.nextLong(5000L, 15000L) } returns 6000L
        Random.nextLong(5000L, 15000L)
        ru.sber.ga.Scanner.getScanData()
        verify { Random.nextLong(5000L, 15000L) }
        verify { ru.sber.ga.Scanner.getScanData() }

        assertEquals(data, ru.sber.ga.Scanner.getScanData())
    }

        @Test
    fun `getScanData should proceed with Random more then 10000`() {

        val data = Random.nextBytes(100)
        every { ru.sber.ga.Scanner.getScanData() } returns data
        every { Random.nextLong(5000L, 15000L) } returns 11000L
        Random.nextLong(5000L, 15000L)
        ru.sber.ga.Scanner.getScanData()
        verify { Random.nextLong(5000L, 15000L)}
        verify { ru.sber.ga.Scanner.getScanData() }

        assertEquals(data, ru.sber.ga.Scanner.getScanData())
    }

    @AfterEach
    fun tearDown() {
        unmockkAll()
    }
}