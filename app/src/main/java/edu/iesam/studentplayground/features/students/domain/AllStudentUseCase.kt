package edu.iesam.studentplayground.features.students.domain

class AllStudentUseCase (val studentRepository: StudentRepository) {

    fun allStudents(): List<Student> {
        return studentRepository.allStudents()
    }
}