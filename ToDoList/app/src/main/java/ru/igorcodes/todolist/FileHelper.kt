package ru.igorcodes.todolist
import android.content.Context
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

object FileHelper {
    private val filename = "listinfo.dat"

    fun writeData(item: ArrayList<String>, context: Context) {
        val fos: FileOutputStream = context.openFileOutput(filename, Context.MODE_PRIVATE)
        val oas = ObjectOutputStream(fos)
        oas.writeObject(item)
        oas.close()
    }

    fun readData(context: Context) : ArrayList<String> {
        try {
            val fis: FileInputStream = context.openFileInput(filename)
            val ois = ObjectInputStream(fis)
            @Suppress("UNCHECKED_CAST")
            return ois.readObject() as ArrayList<String>
        } catch(error: FileNotFoundException) {
            return ArrayList<String>()
        }
    }
}