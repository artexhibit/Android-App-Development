package ru.igorcodes.kotlinbasics.firebase
import android.Manifest
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
import ru.igorcodes.kotlinbasics.databinding.ActivityFirebaseUpdateUserBinding
import java.util.UUID

class FirebaseUpdateUserActivity: AppCompatActivity() {

    private lateinit var updateUserBiding: ActivityFirebaseUpdateUserBinding
    private lateinit var activityResultLauncher: ActivityResultLauncher<Intent>
    private var imageUri: Uri? = null
    private val firebaseStorage: FirebaseStorage = FirebaseStorage.getInstance()
    private val storageRef: StorageReference = firebaseStorage.reference

    val database: FirebaseDatabase = FirebaseDatabase.getInstance()
    var myReference: DatabaseReference = database.reference.child("MyUsers")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        updateUserBiding = ActivityFirebaseUpdateUserBinding.inflate(layoutInflater)
        val view = updateUserBiding.root

        enableEdgeToEdge()
        setContentView(view)
        registerActivityResultLauncher()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        supportActionBar?.title = "Update User"
        getAndSetData()

        updateUserBiding.buttonUpdateUser.setOnClickListener { uploadPhoto() }
        updateUserBiding.userUpdateProfileImage.setOnClickListener { chooseImage() }
    }

    private fun chooseImage() {
        val intent = Intent()

        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        activityResultLauncher.launch(intent)
    }

    private fun registerActivityResultLauncher() {
        activityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult(), ActivityResultCallback { result ->
            val resultCode = result.resultCode
            val imageData = result.data

            if (resultCode == RESULT_OK && imageData != null) {
                imageUri = imageData.data
                imageUri?.let { Picasso.get().load(it).into(updateUserBiding.userUpdateProfileImage) }
            }
        })
    }

    private fun uploadPhoto() {
        updateUserBiding.buttonUpdateUser.isClickable = false
        updateUserBiding.progressBarUpdateUser.visibility = View.VISIBLE

        val imageName = intent.getStringExtra("imageName").toString()
        val imageReference = storageRef.child("images").child(imageName)
        imageUri?.let { uri ->
            imageReference.putFile(uri).addOnSuccessListener {
                Toast.makeText(applicationContext, "Image updated", Toast.LENGTH_SHORT).show()

                val myUploadedImageReference = storageRef.child("images").child(imageName)

                myUploadedImageReference.downloadUrl.addOnSuccessListener {  url ->
                    val imageURL = url.toString()
                    updateData(imageURL, imageName)
                }
            }.addOnFailureListener {
                Toast.makeText(applicationContext, it.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getAndSetData() {
        val name = intent.getStringExtra("name")
        val age = intent.getIntExtra("age", 0).toString()
        val email = intent.getStringExtra("email")
        val imageURL = intent.getStringExtra("imageURL").toString()

        updateUserBiding.editTextFirebaseUpdateName.setText(name)
        updateUserBiding.editTextFirebaseUpdateAge.setText(age)
        updateUserBiding.editTextFirebaseUpdateEmail.setText(email)
        Picasso.get().load(imageURL).into(updateUserBiding.userUpdateProfileImage)
    }

    private fun updateData(imageURL: String, imageName: String) {
        val updatedName = updateUserBiding.editTextFirebaseUpdateName.text.toString()
        val updatedAge = updateUserBiding.editTextFirebaseUpdateAge.text.toString().toInt()
        val updatedEmail = updateUserBiding.editTextFirebaseUpdateEmail.text.toString()
        val userID = intent.getStringExtra("id").toString()

        val userMap = mutableMapOf<String, Any>()
        userMap["userID"] = userID
        userMap["userName"] = updatedName
        userMap["userAge"] = updatedAge
        userMap["userEmail"] = updatedEmail
        userMap["url"] = imageURL
        userMap["imageName"] = imageName

        myReference.child(userID).updateChildren(userMap).addOnCompleteListener {  task ->
            if (task.isSuccessful) {
                Toast.makeText(applicationContext, "The user has been updated", Toast.LENGTH_SHORT).show()
                updateUserBiding.buttonUpdateUser.isClickable = true
                updateUserBiding.progressBarUpdateUser.visibility = View.INVISIBLE
                finish()
            }
        }
    }
}