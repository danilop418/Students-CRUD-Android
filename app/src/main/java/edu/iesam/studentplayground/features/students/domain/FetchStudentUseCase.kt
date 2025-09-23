package edu.iesam.studentplayground.features.students.domain

class FetchStudentUseCase(val studentRepository: StudentRepository) {

    fun fecth(exp: String): Student? {
        return studentRepository.search(exp)
    }
}