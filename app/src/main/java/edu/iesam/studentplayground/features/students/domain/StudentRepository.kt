package edu.iesam.studentplayground.features.students.domain

interface StudentRepository {

    fun save(student: Student)

    fun search(exp: String): Student?

    fun update(name: String, student: Student): Student?

    fun delete(exp: String)

    fun fetch(): List<Student>

    fun exist(exp:String):Boolean

}