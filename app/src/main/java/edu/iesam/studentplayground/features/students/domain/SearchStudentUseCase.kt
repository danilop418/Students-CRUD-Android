package edu.iesam.studentplayground.features.students.domain

class SearchStudentUseCase(val studentRepository: StudentRepository) {

    fun search(exp: String): Student? {
        return studentRepository.search(exp)
    }
}