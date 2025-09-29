package edu.iesam.studentplayground.features.students.domain

import edu.iesam.studentplayground.features.students.domain.exception.StudentAlreadyExistException
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import org.junit.Assert.*
import org.junit.Test
import io.mockk.mockk
import io.mockk.verify

class SaveStudentUseCaseTest {

    @Test
    fun `when invoke then save student`() {
        //Given
        val studentRepositoryMockk = mockk<StudentRepository>(relaxed = true)
        val existStudentUseCase = ExistStudentUseCase(studentRepositoryMockk)
        val saveStudentUseCase = SaveStudentUseCase(studentRepositoryMockk, existStudentUseCase)
        val student = Student("01", "Dani")

        //When
        saveStudentUseCase.save(student)

        //Then
        verify(exactly = 1) { studentRepositoryMockk.save(student) }
    }

    @Test
    fun `when student has empty exp`() {
        //Given
        val studentRepositoryMockk = mockk<StudentRepository>(relaxed = true)
        val existStudentUseCase = ExistStudentUseCase(studentRepositoryMockk)
        val saveStudentUseCase = SaveStudentUseCase(studentRepositoryMockk, existStudentUseCase)
        val student = Student("", "Dani")

        //When && Then
        assertThrows(IllegalArgumentException::class.java) {
            saveStudentUseCase.save(student)
        }

        verify(exactly = 0) { studentRepositoryMockk.save(student) }
    }

    @Test
    fun `when student has empty name`() {
        //Given
        val studentRepositoryMockk = mockk<StudentRepository>(relaxed = true)
        val existStudentUseCase = ExistStudentUseCase(studentRepositoryMockk)
        val saveStudentUseCase = SaveStudentUseCase(studentRepositoryMockk, existStudentUseCase)
        val student = Student("01", "")

        //When && Then
        assertThrows(IllegalArgumentException::class.java) {
            saveStudentUseCase.save(student)
        }

        verify(exactly = 0) { studentRepositoryMockk.save(student) }
    }

    @Test
    fun `when the id of student already exist`() {
        //Given
        val studentRepositoryMockk = mockk<StudentRepository>(relaxed = true)
        val existStudentUseCase = ExistStudentUseCase(studentRepositoryMockk)
        val saveStudentUseCase = SaveStudentUseCase(studentRepositoryMockk, existStudentUseCase)

        val student1 = Student("01", "Dani")
        val student2 = Student("01", "Juan")

        every { studentRepositoryMockk.exist(student1.exp) } returns false
        every { studentRepositoryMockk.save(student1) } just Runs

        //When
        saveStudentUseCase.save(student1)

        every { studentRepositoryMockk.exist(student2.exp) } returns true

        //Then
        assertThrows(StudentAlreadyExistException::class.java) {
            saveStudentUseCase.save(student2)
        }
        verify(exactly = 2) { studentRepositoryMockk.exist(student1.exp) }
        verify(exactly = 1) { studentRepositoryMockk.save(student1) }
        verify(exactly = 0) { studentRepositoryMockk.save(student2) }

    }
}