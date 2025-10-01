package edu.iesam.studentplayground.features.students.domain

import edu.iesam.studentplayground.features.students.domain.errors.ErrorApp

class SaveStudentUseCase(
    private val studentRepository: StudentRepository,
    private val existStudentUseCase: ExistStudentUseCase
) {
    operator fun invoke(student: Student): Result<Unit> {
        if (student.exp.isEmpty()) {
            return Result.failure(ErrorApp.EmptyExpedient)
        }
        if (student.name.isEmpty()) {
            return Result.failure(ErrorApp.EmptyName)
        }
        if (existStudentUseCase.exist(student.exp)) {
            return Result.failure(ErrorApp.StudentAlreadyExists)
        }

        studentRepository.save(student)
        return Result.success(Unit)
    }
}