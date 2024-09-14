package ru.igorcodes.photoalbum.view
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore.Images
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.igorcodes.photoalbum.R
import ru.igorcodes.photoalbum.databinding.ActivityAddImageBinding
import ru.igorcodes.photoalbum.databinding.ActivityUpdateImageBinding
import ru.igorcodes.photoalbum.model.MyImages
import ru.igorcodes.photoalbum.utile.ConvertImage
import ru.igorcodes.photoalbum.viewModel.MyImagesViewModel

class UpdateImageActivity : AppCompatActivity() {

    private lateinit var activityResultLauncherForSelectImage: ActivityResultLauncher<Intent>
    private lateinit var updateImageBinding: ActivityUpdateImageBinding
    private lateinit var viewModel: MyImagesViewModel
    private var id = -1
    private var control = false
    private var imageAsString = ""
    private lateinit var selectedImage: Bitmap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        updateImageBinding = ActivityUpdateImageBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(updateImageBinding.root)

        viewModel = ViewModelProvider(this)[MyImagesViewModel::class.java]
        getAndSetData()
        registerActivityForSelectImage()


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        updateImageBinding.imageViewUpdateImage.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, Images.Media.EXTERNAL_CONTENT_URI)
            activityResultLauncherForSelectImage.launch(intent)
        }

        updateImageBinding.buttonUpdate.setOnClickListener {

            updateImageBinding.buttonUpdate.text = "Updating, please wait"
            updateImageBinding.buttonUpdate.isEnabled = false

            GlobalScope.launch(Dispatchers.IO) {
                val updatedTitle = updateImageBinding.editTextUpdateTitle.text.toString()
                val updatedDescription = updateImageBinding.editTextUpdateDescription.text.toString()

                if (control) {
                    val newImageAsString = ConvertImage.convertToString(selectedImage)

                    if (newImageAsString != null) {
                        imageAsString = newImageAsString
                    } else {
                        Toast.makeText(applicationContext, "There is a problem, select a new image", Toast.LENGTH_SHORT).show()
                    }
                }

                val myUpdatedImage = MyImages(updatedTitle, updatedDescription, imageAsString)
                myUpdatedImage.imageID = id
                viewModel.update(myUpdatedImage)
                finish()
            }
        }

        updateImageBinding.toolbarUpdateImage.setNavigationOnClickListener { finish() }
    }

    private fun getAndSetData() {
        id = intent.getIntExtra("id", -1)

        if (id != -1) {
            CoroutineScope(Dispatchers.IO).launch {
                val myImage = viewModel.getItemByID(id)

                withContext(Dispatchers.Main) {
                    updateImageBinding.editTextUpdateTitle.setText(myImage.imageTitle)
                    updateImageBinding.editTextUpdateDescription.setText(myImage.imageDescription)
                    imageAsString = myImage.imageAsString
                    val imageAsBitmap = ConvertImage.convertToBitmap(imageAsString)
                    updateImageBinding.imageViewUpdateImage.setImageBitmap(imageAsBitmap)
                }
            }
        }
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
                    updateImageBinding.imageViewUpdateImage.setImageBitmap(selectedImage)
                    control = true
                }
            }
        }
    }
}