package edu.iesam.studentplayground.features.students.domain

import edu.iesam.studentplayground.features.students.domain.exception.StudentAlreadyExistException

class SaveStudentUseCase(
    val studentRepository: StudentRepository,
    val existStudentUseCase: ExistStudentUseCase) {
    fun save(student: Student) {
        if (student.exp.isEmpty() || student.exp.isBlank()) {
            throw IllegalArgumentException("The expedient cannot is empty")
        }
        if (student.name.isEmpty() || student.name.isBlank()) {
            throw IllegalArgumentException("The name cannot is empty")
        }
        if (existStudentUseCase.exist(student.exp)) {
            throw StudentAlreadyExistException("The id of Student already exist")
        }
        studentRepository.save(student)
    }

}