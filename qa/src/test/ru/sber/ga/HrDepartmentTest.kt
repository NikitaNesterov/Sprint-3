package ru.sber.ga

import io.mockk.*
import io.mockk.impl.annotations.RelaxedMockK
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach

import org.junit.jupiter.api.Test
import ru.sber.qa.Certificate
import ru.sber.qa.HrDepartment
import java.util.*
import kotlin.random.Random
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertSame


/**
 * @author Nikita Nesterov
 */
internal class HrDepartmentTest {

    @RelaxedMockK
    lateinit var incomeBox: LinkedList<CertificateRequest>
    lateinit var outcomeOutcome: LinkedList<Certificate>


    @BeforeEach
    fun setUp() {
        mockkClass(Certificate::class)
        mockkObject(HrDepartment)
        mockkClass(CertificateType::class)
        incomeBox = LinkedList()
        outcomeOutcome = LinkedList()
    }


    @Test
    fun `receiveRequest throws WeekendDayException`() {

        val certificateRequest =
            CertificateRequest(employeeNumber = 2541L, certificateType = CertificateType.NDFL)

        every { HrDepartment.receiveRequest(certificateRequest) } throws WeekendDayException()

        assertFailsWith(
            exceptionClass = WeekendDayException::class,
            block = { HrDepartment.receiveRequest(certificateRequest) }
        )
    }

    @Test
    fun `receiveRequest throws NotAllowedrequestException`() {

        val certificateRequest =
            CertificateRequest(employeeNumber = 2541L, certificateType = CertificateType.NDFL)

        every { HrDepartment.receiveRequest(certificateRequest) } throws NotAllowReceiveRequestException()

        assertFailsWith(
            exceptionClass = NotAllowReceiveRequestException::class,
            block = { HrDepartment.receiveRequest(certificateRequest) }
        )
    }

    @Test
    fun `receiveRequest should push into income`() {

        val certificateRequest =
            CertificateRequest(employeeNumber = 2541L, certificateType = CertificateType.NDFL)

        assertSame(incomeBox.push(certificateRequest), HrDepartment.receiveRequest(certificateRequest))

    }

    @Test
    fun `processNextRequest should push into outcomeOutcome`() {

        val hrEmployeeNumber = 6532L
        val mockkCertificateRequest = mockkClass(CertificateRequest::class)

        val myCertificate = Certificate(
            certificateRequest = mockkCertificateRequest,
            processedBy = hrEmployeeNumber,
            data = Scanner.getScanData()
        )
        every { mockkCertificateRequest.process(hrEmployeeNumber) } returns myCertificate
        every { HrDepartment.receiveRequest(mockkCertificateRequest) } returns incomeBox.push(mockkCertificateRequest)
        HrDepartment.receiveRequest(mockkCertificateRequest)
        mockkCertificateRequest.process(hrEmployeeNumber)
        verify { HrDepartment.receiveRequest(mockkCertificateRequest) }
        verify { mockkCertificateRequest.process(hrEmployeeNumber) }

        assertSame(outcomeOutcome.push(myCertificate), HrDepartment.processNextRequest(hrEmployeeNumber))
    }

    @AfterEach
    fun afterTEsts() {
        unmockkAll()
    }
}
