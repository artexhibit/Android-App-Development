package ru.igorcodes.noteapp.Repository
import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow
import ru.igorcodes.noteapp.Model.Note
import ru.igorcodes.noteapp.Room.NoteDAO

class NoteRepository(private val noteDAO: NoteDAO) {
    val myAllNotes: Flow<List<Note>> = noteDAO.getAllNotes()

    @WorkerThread
    suspend fun insert(note: Note) {
        noteDAO.insert(note)
    }

    @WorkerThread
    suspend fun update(note: Note) {
        noteDAO.update(note)
    }

    @WorkerThread
    suspend fun delete(note: Note) {
        noteDAO.delete(note)
    }

    @WorkerThread
    suspend fun deleteAllNotes() {
        noteDAO.deleteAllNotes()
    }
}