package ru.igorcodes.photoalbum.room
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import ru.igorcodes.photoalbum.model.MyImages

@Dao
interface MyImagesDAO {
    @Insert
    suspend fun insert(myImages: MyImages)

    @Delete
    suspend fun delete(myImages: MyImages)

    @Update
    suspend fun update(myImages: MyImages)

    @Query("SELECT * FROM my_images ORDER BY imageID ASC")
    fun getAllImages() : LiveData<List<MyImages>>
}