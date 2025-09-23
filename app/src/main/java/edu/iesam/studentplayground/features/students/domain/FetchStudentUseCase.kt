package edu.iesam.studentplayground.features.students.domain

class FetchStudentUseCase(val studentRepository: StudentRepository) {

    operator fun invoke(exp: String): Student? {
        return studentRepository.search(exp)
    }
}