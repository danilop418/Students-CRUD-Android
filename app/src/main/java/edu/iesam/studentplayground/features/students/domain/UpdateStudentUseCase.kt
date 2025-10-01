package edu.iesam.studentplayground.features.students.domain

class UpdateStudentUseCase(val studentRepository: StudentRepository) {
    fun update(name: String, student: Student): Result<Student> {
        val updated = studentRepository.update(name, student)
        return Result.success(updated)
    }
}