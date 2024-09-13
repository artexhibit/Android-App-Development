package ru.igorcodes.photoalbum.view
import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore.*
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ru.igorcodes.photoalbum.R
import ru.igorcodes.photoalbum.databinding.ActivityAddImageBinding
import ru.igorcodes.photoalbum.utile.ControlPermission

class AddImageActivity: AppCompatActivity() {

    lateinit var addImageBinding: ActivityAddImageBinding
    lateinit var activityResultLauncherForSelectImage: ActivityResultLauncher<Intent>
    lateinit var selectedImage: Bitmap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        addImageBinding = ActivityAddImageBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(addImageBinding.root)

        registerActivityForSelectImage()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        addImageBinding.imageViewAddImage.setOnClickListener {
            if (ControlPermission.checkPermission(this)) {
                val intent = Intent(Intent.ACTION_PICK, Images.Media.EXTERNAL_CONTENT_URI)
                activityResultLauncherForSelectImage.launch(intent)
            } else {
                if (Build.VERSION.SDK_INT >= 33) {
                    ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_MEDIA_IMAGES), 1)
                } else {
                    ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 1)
                }
            }
        }

        addImageBinding.buttonAdd.setOnClickListener {

        }

        addImageBinding.toolbarAddImage.setNavigationOnClickListener { finish() }
    }

    private fun registerActivityForSelectImage() {
        activityResultLauncherForSelectImage = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            val resultCode = result.resultCode
            val imageData = result.data

            if (resultCode == RESULT_OK && imageData != null) {
                val imageUri = imageData.data

                imageUri?.let {
                    selectedImage = if (Build.VERSION.SDK_INT >= 28) {
                        val imageSource = ImageDecoder.createSource(this.contentResolver, it)
                        ImageDecoder.decodeBitmap(imageSource)
                    } else {
                        Images.Media.getBitmap(this.contentResolver, imageUri)
                    }
                    addImageBinding.imageViewAddImage.setImageBitmap(selectedImage)
                }
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == 1 && grantResults.isNotEmpty() && grantResults.first() == PackageManager.PERMISSION_GRANTED) {
            val intent = Intent(Intent.ACTION_PICK, Images.Media.EXTERNAL_CONTENT_URI)
            activityResultLauncherForSelectImage.launch(intent)
        }
    }
}