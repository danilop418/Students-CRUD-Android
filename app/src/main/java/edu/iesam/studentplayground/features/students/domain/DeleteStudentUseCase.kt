package edu.iesam.studentplayground.features.students.domain

class DeleteStudentUseCase(val studentRepository: StudentRepository) {

    operator fun invoke(exp: String) {
        studentRepository.delete(exp)
    }
}