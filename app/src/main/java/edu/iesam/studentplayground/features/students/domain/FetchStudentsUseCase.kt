package edu.iesam.studentplayground.features.students.domain

import edu.iesam.studentplayground.features.students.domain.errors.ErrorApp

class FetchStudentsUseCase(val studentRepository: StudentRepository) {

    fun fetch(): Result<List<Student>> {
        val students = studentRepository.fetch()
        return if (students.isNotEmpty()) {
            Result.success(students)
        } else {
            Result.failure(ErrorApp.StudentNotFound)
        }
    }
}