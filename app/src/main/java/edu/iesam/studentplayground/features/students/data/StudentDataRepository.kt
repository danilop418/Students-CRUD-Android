package edu.iesam.studentplayground.features.students.data

import edu.iesam.studentplayground.features.students.data.local.StudentMemLocalDataSource
import edu.iesam.studentplayground.features.students.data.local.StudentXmlLocalDataSource
import edu.iesam.studentplayground.features.students.data.remote.StudentApiRemoteDataSource
import edu.iesam.studentplayground.features.students.domain.Student
import edu.iesam.studentplayground.features.students.domain.StudentRepository

class StudentDataRepository(
    private val xmlLocalDataSource: StudentXmlLocalDataSource,
    private val memLocalDataSource: StudentMemLocalDataSource,
    private val apiRemoteDataSource: StudentApiRemoteDataSource
) : StudentRepository {

    override fun save(student: Student) {
        memLocalDataSource.save(student)
    }

    override fun search(exp: String): Student? {
        return memLocalDataSource.search(exp)
    }

    override fun update(name: String, student: Student): Student? {
        return memLocalDataSource.update(name, student)
    }

    override fun delete(exp: String) {
        memLocalDataSource.delete(exp)
    }

    override fun fetch(): List<Student> {
        return memLocalDataSource.allStudents()
    }
}