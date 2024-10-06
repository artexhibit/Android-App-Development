package ru.igorcodes.kotlinbasics.firebase
import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.squareup.picasso.Picasso
import ru.igorcodes.kotlinbasics.R
import ru.igorcodes.kotlinbasics.databinding.ActivityFirebaseAddUserBinding
import java.util.UUID

class FirebaseAddUserActivity: AppCompatActivity() {

    private lateinit var addUserBiding: ActivityFirebaseAddUserBinding
    private val database: FirebaseDatabase = FirebaseDatabase.getInstance()
    private val myReference: DatabaseReference = database.reference.child("MyUsers")
    private lateinit var activityResultLauncher: ActivityResultLauncher<Intent>
    private var imageUri: Uri? = null
    private val firebaseStorage: FirebaseStorage = FirebaseStorage.getInstance()
    private val storageRef: StorageReference = firebaseStorage.reference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addUserBiding = ActivityFirebaseAddUserBinding.inflate(layoutInflater)
        val view = addUserBiding.root

        enableEdgeToEdge()
        setContentView(view)
        registerActivityResultLauncher()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        addUserBiding.buttonAddUser.setOnClickListener { uploadPhoto() }
        addUserBiding.userProfileImage.setOnClickListener { chooseImage() }
    }

    private fun chooseImage() {
        val permission = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            Manifest.permission.READ_MEDIA_IMAGES
        } else {
            Manifest.permission.READ_EXTERNAL_STORAGE
        }

        if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(permission), 1)
        } else {
            val intent = Intent()

            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            activityResultLauncher.launch(intent)
        }
    }

    private fun registerActivityResultLauncher() {
        activityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult(), ActivityResultCallback { result ->
            val resultCode = result.resultCode
            val imageData = result.data

            if (resultCode == RESULT_OK && imageData != null) {
                imageUri = imageData.data
                imageUri?.let { Picasso.get().load(it).into(addUserBiding.userProfileImage) }
            }
        })
    }

    private fun addUserToDatabase(url: String, imageName: String) {
        val name = addUserBiding.editTextFirebaseName.text.toString()
        val age = addUserBiding.editTextFirebaseAge.text.toString().toInt()
        val email = addUserBiding.editTextFirebaseEmail.text.toString()
        val id = myReference.push().key.toString()
        val user = Users(name, age, email, id, url, imageName)

        myReference.child(id).setValue(user).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(applicationContext, "The user has been added to the database", Toast.LENGTH_SHORT).show()
                addUserBiding.buttonAddUser.isClickable = true
                addUserBiding.progressBarAddUser.visibility = View.INVISIBLE
                finish()
            } else {
                Toast.makeText(applicationContext, task.exception.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun uploadPhoto() {
        addUserBiding.buttonAddUser.isClickable = false
        addUserBiding.progressBarAddUser.visibility = View.VISIBLE

        val imageName = UUID.randomUUID().toString()
        val imageReference = storageRef.child("images").child(imageName)
        imageUri?.let { uri ->
            imageReference.putFile(uri).addOnSuccessListener {
                Toast.makeText(applicationContext, "Image uploaded", Toast.LENGTH_SHORT).show()

                val myUploadedImageReference = storageRef.child("images").child(imageName)

                myUploadedImageReference.downloadUrl.addOnSuccessListener {  url ->
                    val imageURL = url.toString()
                    addUserToDatabase(imageURL, imageName)
                }
             }.addOnFailureListener {
                Toast.makeText(applicationContext, it.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == 1 && grantResults.isNotEmpty() && grantResults.first() == PackageManager.PERMISSION_GRANTED) {
            val intent = Intent()

            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            activityResultLauncher.launch(intent)
        } else {
            //show Toast
        }
    }
}