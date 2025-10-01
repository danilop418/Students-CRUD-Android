package edu.iesam.studentplayground.features.students.domain


class DeleteStudentUseCase(val studentRepository: StudentRepository) {

    fun delete(exp: String): Result<Unit> {
        val student = studentRepository.search(exp)
        return run {
            studentRepository.delete(student.exp)
            Result.success(Unit)
        }
    }
}