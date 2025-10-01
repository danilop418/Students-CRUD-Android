package edu.iesam.studentplayground.features.students.presentation

import androidx.lifecycle.ViewModel
import edu.iesam.studentplayground.features.students.domain.FetchStudentsUseCase
import edu.iesam.studentplayground.features.students.domain.DeleteStudentUseCase
import edu.iesam.studentplayground.features.students.domain.SearchStudentUseCase
import edu.iesam.studentplayground.features.students.domain.SaveStudentUseCase
import edu.iesam.studentplayground.features.students.domain.Student
import edu.iesam.studentplayground.features.students.domain.UpdateStudentUseCase

class StudentViewModel(
    private val saveStudentUseCase: SaveStudentUseCase,
    private val searchStudentsUseCase: SearchStudentUseCase,
    private val updateStudentUseCase: UpdateStudentUseCase,
    private val deleteStudentUseCase: DeleteStudentUseCase,
    private val fetchStudentUseCase: FetchStudentsUseCase
) : ViewModel() {
    fun saveClicked(exp: String, name: String): Result<Unit> {
        val student = Student(exp, name)
        return saveStudentUseCase(student)
    }

    fun searchStudent(exp: String): Student {
        return searchStudentsUseCase.search(exp)
    }

    fun updateStudent(name: String, student: Student): Student {
        return updateStudentUseCase.update(name, student)
    }

    fun deleteStudent(exp: String) {
        return deleteStudentUseCase.delete(exp)
    }

    fun fetch(): List<Student> {
        return fetchStudentUseCase.fetch()
    }
}