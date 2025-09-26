package edu.iesam.studentplayground.features.students.domain

import io.mockk.mockk
import org.junit.Assert.*
import org.junit.Test

class SaveStudentUseCaseTest {

    /**
     * When .... Then ....
     */
    @Test
    fun `when invoke then save student`(){
        //Given
        val studentRepositoryMock = mockk<StudentRepository>()
        val saveStudentUseCase = SaveStudentUseCase(studentRepositoryMock)

        //When


        //Then

    }

}