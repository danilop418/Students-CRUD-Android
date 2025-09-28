package edu.iesam.studentplayground.features.students.domain

import org.junit.Assert.*
import org.junit.Test
import io.mockk.mockk
import io.mockk.verify

class SaveStudentUseCaseTest {

    @Test
    fun `when invoke then save student`(){
        //Given
        val studentRepositoryMock = mockk<StudentRepository>(relaxed = true)
        val saveStudentUseCase = SaveStudentUseCase(studentRepositoryMock)
        val student = Student("001","Name")

        //When
        saveStudentUseCase.save(student)

        //Then
        verify (exactly = 1){ studentRepositoryMock.save(student)  }
    }

    @Test
    fun `when student has empty exp`() {
        //Given
        val studentRepositoryMock = mockk<StudentRepository>(relaxed = true)
        val saveStudentUseCase = SaveStudentUseCase(studentRepositoryMock)
        val student = Student("","Name")

        //When && Then
        assertThrows(IllegalArgumentException::class.java){
            saveStudentUseCase.save(student)
        }
    }

    @Test
    fun `when student has empty name`() {
        //Given
        val studentRepositoryMock = mockk<StudentRepository>(relaxed = true)
        val saveStudentUseCase = SaveStudentUseCase(studentRepositoryMock)
        val student = Student("003","")

        //When && Then
        assertThrows(IllegalArgumentException::class.java){
            saveStudentUseCase.save(student)
        }
    }
}