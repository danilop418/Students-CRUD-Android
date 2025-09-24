package edu.iesam.studentplayground.features.students.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import edu.iesam.studentplayground.R
import edu.iesam.studentplayground.features.students.data.remote.StudentApiRemoteDataSource
import edu.iesam.studentplayground.features.students.data.remote.StudentDataRepository
import edu.iesam.studentplayground.features.students.data.local.StudentMemLocalDataSource
import edu.iesam.studentplayground.features.students.data.local.StudentXmlLocalDataSource
import edu.iesam.studentplayground.features.students.domain.AllStudentUseCase
import edu.iesam.studentplayground.features.students.domain.DeleteStudentUseCase
import edu.iesam.studentplayground.features.students.domain.FetchStudentUseCase
import edu.iesam.studentplayground.features.students.domain.SaveStudentUseCase
import edu.iesam.studentplayground.features.students.domain.UpdateStudentUseCase

class StudentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initStudents()
    }

    fun initStudents() {
        val xml = StudentXmlLocalDataSource()
        val mem = StudentMemLocalDataSource()
        val api = StudentApiRemoteDataSource()
        val dataRepository = StudentDataRepository(xml, mem, api)
        val useCase = SaveStudentUseCase(dataRepository)
        val fetchStudentUseCase = FetchStudentUseCase(dataRepository)
        val updateStudentUseCase = UpdateStudentUseCase(dataRepository)
        val allStudentUseCase = AllStudentUseCase(dataRepository)
        val deleteStudentUseCase = DeleteStudentUseCase(dataRepository)

        val viewModel = StudentViewModel(
            useCase,
            fetchStudentUseCase,
            updateStudentUseCase,
            deleteStudentUseCase,
            allStudentUseCase
        )
        //Create
        viewModel.saveClicked("0001", "nombre1 apellido1 apellido1")
        Log.d("@dev", "Stop")

        viewModel.saveClicked("0002", "nombre2 apellido2 apellido2")
        Log.d("@dev", "Stop")
        //Search
        val student = viewModel.searchStudent("0001")
        val studentSecond = viewModel.searchStudent("0002")
        //Update
        if (student != null) {
            viewModel.updateStudent("Dani", student)
        } else {
            Log.d("@dev", "Estudiante no es encontrado")
        }
        //Delete
        if (studentSecond != null) {
            viewModel.deleteStudent(studentSecond.exp)
        } else {
            Log.d("@dev", "Estudiante no es encontrado")
        }
        //Show
        viewModel.allStudents()
    }
}