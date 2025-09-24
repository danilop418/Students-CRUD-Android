package edu.iesam.studentplayground.features.students.domain

class SaveStudentUseCase(val studentRepository: StudentRepository) {
    fun save(student: Student){
        studentRepository.save(student)
    }

}