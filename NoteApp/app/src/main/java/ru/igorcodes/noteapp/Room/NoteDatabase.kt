package ru.igorcodes.noteapp.Room
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import ru.igorcodes.noteapp.Model.Note

@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase: RoomDatabase() {
    abstract fun getNoteDAO(): NoteDAO

    //Singleton
    companion object {
        @Volatile
        private var instance: NoteDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): NoteDatabase {
            return instance ?: synchronized(this) {
                val newInstance = Room.databaseBuilder(context.applicationContext, NoteDatabase::class.java, "note_database").addCallback(NoteDatabaseCallback(scope)).build()
                instance = newInstance
                newInstance
            }
        }
    }

    private class NoteDatabaseCallback(private val scope: CoroutineScope): RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            instance?.let { database ->
                scope.launch {
                    val noteDAO = database.getNoteDAO()
                    noteDAO.insert(Note("Title 1", "Description 1"))
                    noteDAO.insert(Note("Title 2", "Description 2"))
                    noteDAO.insert(Note("Title 3", "Description 3"))
                }
            }
        }
    }
}