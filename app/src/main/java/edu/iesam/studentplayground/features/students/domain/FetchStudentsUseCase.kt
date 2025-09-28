package edu.iesam.studentplayground.features.students.domain

class FetchStudentsUseCase(val studentRepository: StudentRepository) {

    fun fetch(): List<Student> {
        return studentRepository.fetch()
    }
}