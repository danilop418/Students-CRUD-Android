package edu.iesam.studentplayground.features.students.data.local

import edu.iesam.studentplayground.features.students.domain.Student

class StudentMemLocalDataSource {

    private val dataSource: MutableMap<String, Student> = mutableMapOf()

    fun save(student: Student) {
        dataSource.put(student.exp, student)
    }

    fun search(exp: String): Student? {
        if (dataSource.containsKey(exp)) {
            return dataSource[exp]
        }
        return null
    }

    fun update(name: String, student: Student): Student? {
        if (dataSource.containsKey(student.exp)) {
            val newStudent = Student(student.exp, name)
            dataSource.put(newStudent.exp, newStudent)
            return newStudent
        }
        return null
    }

    fun delete(exp: String) {
        dataSource.remove(exp)
    }

    fun allStudents(): List<Student> {
        return dataSource.values.toList()
    }
}