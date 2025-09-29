package edu.iesam.studentplayground.features.students.domain

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class ExistStudentUseCaseTest {

    @Test
    fun `when the id exists, should return true`() {
        //Given
        val studentRepositoryMockk = mockk<StudentRepository>()
        val existStudentUseCase = ExistStudentUseCase(studentRepositoryMockk)
        val studentId = "1"

        every { studentRepositoryMockk.exist(studentId) } returns true

        //When
        val result = existStudentUseCase.exist(studentId)

        //Then
        assertTrue(result)
        verify(exactly = 1) { studentRepositoryMockk.exist(studentId) }
    }

    @Test
    fun `when the id does not exist, should return false`() {
        //Given
        val studentRepositoryMockk = mockk<StudentRepository>()
        val existStudentUseCase = ExistStudentUseCase(studentRepositoryMockk)
        val studentId = "999"

        every { studentRepositoryMockk.exist(studentId) } returns false

        //When
        val result = existStudentUseCase.exist(studentId)

        //Then
        assertFalse(result)
        verify(exactly = 1) { studentRepositoryMockk.exist(studentId) }
    }
}