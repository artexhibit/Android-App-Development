package ru.igorcodes.photoalbum.repository
import android.app.Application
import androidx.lifecycle.LiveData
import ru.igorcodes.photoalbum.model.MyImages
import ru.igorcodes.photoalbum.room.MyImagesDAO
import ru.igorcodes.photoalbum.room.MyImagesDatabase

class MyImagesRepository(application: Application) {
    var myImagesDAO: MyImagesDAO
    var imagesList: LiveData<List<MyImages>>

    init {
        val database = MyImagesDatabase.getDatabaseInstance(application)
        myImagesDAO = database.myImagesDAO()
        imagesList = myImagesDAO.getAllImages()
    }

    suspend fun insert(myImages: MyImages) {
        myImagesDAO.insert(myImages)
    }

    suspend fun delete(myImages: MyImages) {
        myImagesDAO.delete(myImages)
    }

    suspend fun update(myImages: MyImages) {
        myImagesDAO.update(myImages)
    }

    fun getAllImages() : LiveData<List<MyImages>> {
        return imagesList
    }
}