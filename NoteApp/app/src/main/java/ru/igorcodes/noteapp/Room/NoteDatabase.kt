package ru.igorcodes.noteapp.Room
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.igorcodes.noteapp.Model.Note

@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase: RoomDatabase() {
    abstract fun getNoteDAO(): NoteDAO

    //Singleton
    companion object {
        @Volatile
        private var instance: NoteDatabase? = null

        fun getDatabase(context: Context): NoteDatabase {
            return instance ?: synchronized(this) {
                val newInstance = Room.databaseBuilder(context.applicationContext, NoteDatabase::class.java, "note_database").build()
                instance = newInstance
                newInstance
            }
        }
    }
}