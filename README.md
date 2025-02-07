# Notes App 📝
The Notes App is a simple Android application designed to help users create, manage, and store notes locally on their devices. This project was developed to learn and implement Room Database, a local database storage solution provided by Android Jetpack. The app demonstrates how to perform CRUD (Create, Read, Update, Delete) operations using Room Database in a clean and efficient manner.

## Features ✨
Create Notes: Add new notes with a title and description.

**Read Notes**: View all saved notes in a list.

**Update Notes**: Edit existing notes to update their content.

**Delete Notes**: Remove notes that are no longer needed.

**Local Storage**: All notes are stored locally using Room Database, ensuring data persistence even when the app is closed.

**Simple UI**: A clean and intuitive user interface for seamless note management.

# Technologies Used 🛠️
**Kotlin**: The primary programming language used for Android development.

**Room Database**: A part of Android Jetpack, used for local data storage and management.

**ViewModel**: To manage UI-related data in a lifecycle-conscious way.

**LiveData**: To observe data changes and update the UI accordingly.

**RecyclerView**: To display the list of notes efficiently.

**Material Design**: For a modern and user-friendly interface.

# Project Structure 📂
NotesApp/  
│── app/  
│   ├── src/main/java/com/example/notesapp/  
│   │   ├── data/  
│   │   │   ├── Note.kt (Entity class)  
│   │   │   ├── NoteDao.kt (Data Access Object - DAO)  
│   │   │   ├── NoteDatabase.kt (Room Database setup)  
│   │   ├── repository/  
│   │   │   ├── NoteRepository.kt (Data repository)  
│   │   ├── ui/  
│   │   │   ├── NoteViewModel.kt (ViewModel for managing UI data)  
│   │   │   ├── NotesScreen.kt (Compose UI for displaying notes)  
│   │   │   ├── AddEditNoteScreen.kt (Compose UI for adding/editing notes)  

