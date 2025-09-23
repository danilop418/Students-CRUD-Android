package edu.iesam.studentplayground.features.students.domain

class UpdateStudentUseCase(val studentRepository: StudentRepository) {

    fun update(name: String, student: Student): Student? {
        return studentRepository.update(name, student)
    }
}