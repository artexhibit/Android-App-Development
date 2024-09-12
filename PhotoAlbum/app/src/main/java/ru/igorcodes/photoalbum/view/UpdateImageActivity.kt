package ru.igorcodes.photoalbum.view
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ru.igorcodes.photoalbum.R
import ru.igorcodes.photoalbum.databinding.ActivityAddImageBinding
import ru.igorcodes.photoalbum.databinding.ActivityUpdateImageBinding

class UpdateImageActivity : AppCompatActivity() {

    lateinit var updateImageBinding: ActivityUpdateImageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        updateImageBinding = ActivityUpdateImageBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(updateImageBinding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        updateImageBinding.imageViewUpdateImage.setOnClickListener {

        }

        updateImageBinding.buttonUpdate.setOnClickListener {

        }

        updateImageBinding.toolbarUpdateImage.setNavigationOnClickListener {
            finish()
        }
    }
}