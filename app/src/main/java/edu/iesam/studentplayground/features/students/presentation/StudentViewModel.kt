package edu.iesam.studentplayground.features.students.presentation

import androidx.lifecycle.ViewModel
import edu.iesam.studentplayground.features.students.domain.AllStudentUseCase
import edu.iesam.studentplayground.features.students.domain.DeleteStudentUseCase
import edu.iesam.studentplayground.features.students.domain.FetchStudentUseCase
import edu.iesam.studentplayground.features.students.domain.SaveStudentUseCase
import edu.iesam.studentplayground.features.students.domain.Student
import edu.iesam.studentplayground.features.students.domain.UpdateStudentUseCase

class StudentViewModel(
    private val saveStudentUseCase: SaveStudentUseCase,
    private val fetchStudentUseCase: FetchStudentUseCase,
    private val updateStudentUseCase: UpdateStudentUseCase,
    private val deleteStudentUseCase: DeleteStudentUseCase,
    private val allStudentUseCase : AllStudentUseCase
) : ViewModel() {
    fun saveClicked(exp: String, name: String) {
        saveStudentUseCase.save(Student(exp, name))
    }

    fun searchStudent(exp: String): Student? {
        return fetchStudentUseCase.fecth(exp)
    }

    fun updateStudent(name: String, student: Student): Student? {
        return updateStudentUseCase.update(name, student)
    }

    fun deleteStudent(exp: String) {
        deleteStudentUseCase.delete(exp)
    }

    fun allStudents(): List<Student> {
        return allStudentUseCase.allStudents()
    }
}