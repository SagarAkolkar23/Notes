Notes App

Overview

The Notes App is a simple Android application designed to help users create, edit, and delete notes. This project was built to learn and implement Room Database, which provides an abstraction layer over SQLite for efficient local data storage.

Features

Add new notes with a title and description

Edit existing notes

Delete notes

View all saved notes in a RecyclerView

Persistent local storage using Room Database

Technologies Used

Android Studio – IDE for development

Kotlin – Programming language

Jetpack Compose – Modern UI toolkit for building the UI

Room Database – Local database for storing notes

ViewModel – Lifecycle-aware architecture component

LiveData – To observe and update UI data in real-time

Installation

Clone the repository:

git clone https://github.com/yourusername/NotesApp.git

Open the project in Android Studio

Build and run the application on an emulator or physical device

Project Structure

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

Future Enhancements

Implement search functionality for notes

Add categories or labels to notes

Sync notes with cloud storage

License

This project is licensed under the MIT License - see the LICENSE file for details.

