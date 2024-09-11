package ru.igorcodes.photoalbum.room
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.igorcodes.photoalbum.model.MyImages
import kotlin.concurrent.Volatile

@Database(entities = [MyImages::class], version = 1)
abstract class MyImagesDatabase: RoomDatabase() {
    //1 -> annotation
    //2 -> abstract and extend RoomDatabase
    //3 -> abstract method returns DAO instance

    abstract fun myImagesDAO() : MyImagesDAO

    //singleton
    companion object {
        @Volatile
        private var instance: MyImagesDatabase? = null

        fun getDatabaseInstance(context: Context) : MyImagesDatabase {
            synchronized(this) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.applicationContext, MyImagesDatabase::class.java, "my_album").build()
                }
                return instance as MyImagesDatabase
            }
        }
    }
}