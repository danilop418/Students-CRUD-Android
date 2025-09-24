package edu.iesam.studentplayground.features.students.domain

class AllStudentUseCase(val studentRepository: StudentRepository) {

    fun allStudent(): List<Student> {
        return studentRepository.allStudents()
    }
}