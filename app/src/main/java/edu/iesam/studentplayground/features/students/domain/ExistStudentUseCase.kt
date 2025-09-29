package edu.iesam.studentplayground.features.students.domain

class ExistStudentUseCase(val studentRepository: StudentRepository) {

    fun exist(exp: String): Boolean {
        return studentRepository.exist(exp)
    }

}