package edu.iesam.studentplayground.features.students.domain

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class FetchStudentsUseCaseTest {

    @Test
    fun `should return list of students from repository`() {
        //Given
        val studentRepositoryMockk = mockk<StudentRepository>()
        val fetchStudentsUseCase = FetchStudentsUseCase(studentRepositoryMockk)

        val student1 = Student("1", "Marcos")
        val student2 = Student("2", "María")
        val student3 = Student("3", "Juan")
        val expectedStudents = listOf(student1, student2, student3)

        every { studentRepositoryMockk.fetch() } returns expectedStudents

        //When
        val result = fetchStudentsUseCase.fetch()

        //Then
        assertEquals(3, result.size)
        assertEquals(expectedStudents, result)
        assertTrue(result.contains(student1))
        assertTrue(result.contains(student2))
        assertTrue(result.contains(student3))
        verify(exactly = 1) { studentRepositoryMockk.fetch() }
    }

    @Test
    fun `should return empty list when no students exist`() {
        //Given
        val studentRepositoryMockk = mockk<StudentRepository>()
        val fetchStudentsUseCase = FetchStudentsUseCase(studentRepositoryMockk)

        every { studentRepositoryMockk.fetch() } returns emptyList()

        //When
        val result = fetchStudentsUseCase.fetch()

        //Then
        assertTrue(result.isEmpty())
        verify(exactly = 1) { studentRepositoryMockk.fetch() }
    }
}