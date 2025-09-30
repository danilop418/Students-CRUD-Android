package edu.iesam.studentplayground.features.students.domain.errors

sealed class ErrorApp : Throwable() {
    object EmptyExpedient : ErrorApp()
    object EmptyName : ErrorApp()
    object StudentAlreadyExists : ErrorApp()
}