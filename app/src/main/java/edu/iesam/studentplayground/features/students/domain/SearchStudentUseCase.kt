package edu.iesam.studentplayground.features.students.domain

import edu.iesam.studentplayground.features.students.domain.errors.ErrorApp

class SearchStudentUseCase(val studentRepository: StudentRepository) {

    operator fun invoke(exp: String): Result<Student> {
        val student = studentRepository.search(exp)
        return if (student.exp == exp) {
            Result.success(student)
        } else {
            Result.failure(ErrorApp.StudentNotFound)
        }
    }
}