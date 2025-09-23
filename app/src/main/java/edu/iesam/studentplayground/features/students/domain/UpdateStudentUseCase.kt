package edu.iesam.studentplayground.features.students.domain

class UpdateStudentUseCase(val studentRepository: StudentRepository) {

    operator fun invoke(name: String, student: Student): Student? {
        return studentRepository.update(name, student)
    }
}