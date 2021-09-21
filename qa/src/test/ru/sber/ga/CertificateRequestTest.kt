package ru.sber.ga

import io.mockk.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import ru.sber.qa.Certificate
import kotlin.random.Random

/**
 * @author Nikita Nesterov
 */
internal class CertificateRequestTest {


    @Test
    fun processTest() {
        mockkObject(Scanner)

        val certificateRequest =
            CertificateRequest(employeeNumber = 2541L, certificateType = CertificateType.LABOUR_BOOK)

        val hrEmployeeNumber = certificateRequest.employeeNumber

        val data = Random.nextBytes(100)
        every { Scanner.getScanData() } returns data

        val expected = Certificate(certificateRequest, hrEmployeeNumber, data)
        val actual = certificateRequest.process(hrEmployeeNumber)

       assertEquals(expected.certificateRequest, actual.certificateRequest)
        assertEquals(expected.data, actual.data)
        assertEquals(expected.processedBy, actual.processedBy)
    }

}