package ru.igorcodes.noteapp
import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import ru.igorcodes.noteapp.Repository.NoteRepository
import ru.igorcodes.noteapp.Room.NoteDatabase

class NoteApplication: Application() {
    val applicationScope = CoroutineScope(SupervisorJob())
    val database by lazy { NoteDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { NoteRepository(database.getNoteDAO()) }
}