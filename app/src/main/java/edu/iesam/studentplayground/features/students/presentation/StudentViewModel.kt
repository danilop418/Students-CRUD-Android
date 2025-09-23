package edu.iesam.studentplayground.features.students.presentation

import androidx.lifecycle.ViewModel
import edu.iesam.studentplayground.features.students.domain.FetchStudentUseCase
import edu.iesam.studentplayground.features.students.domain.SaveStudentUseCase
import edu.iesam.studentplayground.features.students.domain.Student
import edu.iesam.studentplayground.features.students.domain.UpdateStudentUseCase

class StudentViewModel(
    private val saveStudentUseCase: SaveStudentUseCase,
    private val fetchStudentUseCase: FetchStudentUseCase,
    private val updateStudentUseCase: UpdateStudentUseCase
) : ViewModel() {
    fun saveClicked(exp: String, name: String) {
        saveStudentUseCase.invoke(Student(exp, name))
    }

    fun searchStudent(exp: String): Student? {
        return fetchStudentUseCase.invoke(exp)
    }

    fun updateStudent(name: String, student: Student): Student? {
        return updateStudent(name, student)
    }

    fun deleteStudent(exp: String) {
        deleteStudent(exp)
    }
}