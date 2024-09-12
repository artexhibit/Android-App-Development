package ru.igorcodes.photoalbum.view
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ru.igorcodes.photoalbum.R
import ru.igorcodes.photoalbum.databinding.ActivityAddImageBinding

class AddImageActivity: AppCompatActivity() {

    lateinit var addImageBinding: ActivityAddImageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        addImageBinding = ActivityAddImageBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(addImageBinding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        addImageBinding.imageViewAddImage.setOnClickListener {

        }

        addImageBinding.buttonAdd.setOnClickListener {

        }

        addImageBinding.toolbarAddImage.setNavigationOnClickListener {
            finish()
        }
    }
}