package ru.igorcodes.noteapp.ViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.igorcodes.noteapp.Repository.NoteRepository

class NoteViewModelFactory(private var repository: NoteRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NoteViewModel::class.java)) {
            return NoteViewModel(repository) as T
        } else {
            throw IllegalArgumentException("Unknown View Model")
        }
    }
}