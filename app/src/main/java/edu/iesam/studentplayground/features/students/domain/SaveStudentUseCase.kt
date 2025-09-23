package edu.iesam.studentplayground.features.students.domain

class SaveStudentUseCase(val studentRepository: StudentRepository) {

    operator fun invoke(student: Student){
        studentRepository.save(student)
    }

}