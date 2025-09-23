package edu.iesam.studentplayground.features.students.domain

class DeleteStudentUseCase(val studentRepository: StudentRepository) {

    fun delete(exp: String) {
        studentRepository.delete(exp)
    }
}