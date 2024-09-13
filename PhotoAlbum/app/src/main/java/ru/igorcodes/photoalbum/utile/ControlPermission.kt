package ru.igorcodes.photoalbum.utile
import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.content.ContextCompat

class ControlPermission {
    companion object {
        fun checkPermission(context: Context) : Boolean {
            return when {
                Build.VERSION.SDK_INT >= 34 -> {
                    ContextCompat.checkSelfPermission(context, Manifest.permission.READ_MEDIA_VISUAL_USER_SELECTED) == PackageManager.PERMISSION_GRANTED
                }
                Build.VERSION.SDK_INT >= 33 -> {
                    ContextCompat.checkSelfPermission(context, Manifest.permission.READ_MEDIA_IMAGES) == PackageManager.PERMISSION_GRANTED
                }
                else -> {
                    ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                }
            }
        }
    }
}