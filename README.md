# Proyecto CRUD | Kotlin + Android

I have followed a Clean Code architecture with the Repository pattern. I include a repository that allows the domain and the use cases to connect with the data layer, as well as to switch between local memory, XML, and a local database.
At this moment, the entire project is based on local memory. The views still need to be implemented, and it is pending whether a remote source will be used to store the data.

# Features
Create, update, delete and display students.

# Estructura del proyecto
data
   - local
   - remote
domain
   - usecases
presentation
   - viewmodels
   - activity
