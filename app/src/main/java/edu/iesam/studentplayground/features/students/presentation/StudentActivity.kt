package edu.iesam.studentplayground.features.students.presentation

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import edu.iesam.studentplayground.R
import edu.iesam.studentplayground.features.students.data.remote.StudentApiRemoteDataSource
import edu.iesam.studentplayground.features.students.data.StudentDataRepository
import edu.iesam.studentplayground.features.students.data.local.StudentMemLocalDataSource
import edu.iesam.studentplayground.features.students.data.local.StudentXmlLocalDataSource
import edu.iesam.studentplayground.features.students.domain.FetchStudentsUseCase
import edu.iesam.studentplayground.features.students.domain.DeleteStudentUseCase
import edu.iesam.studentplayground.features.students.domain.ExistStudentUseCase
import edu.iesam.studentplayground.features.students.domain.SearchStudentUseCase
import edu.iesam.studentplayground.features.students.domain.SaveStudentUseCase
import edu.iesam.studentplayground.features.students.domain.UpdateStudentUseCase
import edu.iesam.studentplayground.features.students.domain.errors.ErrorApp

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

        val existStudentUseCase = ExistStudentUseCase(dataRepository)
        val useCase = SaveStudentUseCase(dataRepository, existStudentUseCase)
        val searchStudentUseCase = SearchStudentUseCase(dataRepository)
        val updateStudentUseCase = UpdateStudentUseCase(dataRepository)
        val fetchStudentUseCase = FetchStudentsUseCase(dataRepository)
        val deleteStudentUseCase = DeleteStudentUseCase(dataRepository)

        val viewModel = StudentViewModel(
            useCase,
            searchStudentUseCase,
            updateStudentUseCase,
            deleteStudentUseCase,
            fetchStudentUseCase
        )
        //Create
        val student1 = viewModel.saveClicked("0001", "nombre1 apellido1 apellido1")
        student1.fold(
            onSuccess = {
                Log.d("@dev", "The Student saved correctly")
                toast("Student create")
            },
            onFailure = { error ->
                handleError(error as ErrorApp)
            }
        )
        val student2 = viewModel.saveClicked("0002", "nombre2 apellido2 apellido2")
        student2.fold(
            onSuccess = {
                Log.d("@dev", "The Student saved correctly")
                toast("Student create")
            },
            onFailure = { error ->
                handleError(error as ErrorApp)
            }
        )
        //Search
        val searchStudent1 = viewModel.searchStudent("0001")
        val searchStudent2 = viewModel.searchStudent("0002")

        searchStudent1.fold(
            onSuccess = { student ->
                Log.d("@dev","The Student found ${student.name}")
            },
            onFailure = { error ->
                handleError(error as ErrorApp)
            }
        )

        searchStudent2.fold(
            onSuccess = { student ->
                Log.d("@dev","The Student found ${student.name}")
            },
            onFailure = { error ->
                handleError(error as ErrorApp)
            }
        )

        //Update
        // I will fix this tomorrow

        //Delete
        //I will fix this tomorrow

        //Show
        //I will fix this tomorrow
    }

    private fun handleError(error: ErrorApp) {
        when (error) {
            is ErrorApp.EmptyExpedient -> {
                Log.e("@dev", "Error: The expedient is empty")
                toast("The expedient cannot be empty")
            }

            is ErrorApp.EmptyName -> {
                Log.e("@dev", "Error: The name is empty")
                toast("The name cannot be empty")
            }

            is ErrorApp.StudentAlreadyExists -> {
                Log.e("@dev", "Error: The studient already exist")
                toast("The expedient already exist")
            }
        }
    }

    private fun toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}