package ru.igorcodes.kotlinbasics.firebase
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import ru.igorcodes.kotlinbasics.R
import ru.igorcodes.kotlinbasics.databinding.ActivityFirebaseBinding

class FirebaseActivity: AppCompatActivity() {

    private lateinit var mainBinding: ActivityFirebaseBinding

    private val database: FirebaseDatabase = FirebaseDatabase.getInstance()
    private val myReference: DatabaseReference = database.reference.child("MyUsers")
    private val firebaseStorage: FirebaseStorage = FirebaseStorage.getInstance()
    private val storageReference: StorageReference = firebaseStorage.reference

    private val userList = ArrayList<Users>()
    private val imageNameList = ArrayList<String>()
    private lateinit var usersAdapter: UsersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        mainBinding = ActivityFirebaseBinding.inflate(layoutInflater)
        val view = mainBinding.root
        setContentView(view)
        setSupportActionBar(mainBinding.materialToolbarFirebase)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        mainBinding.floatingActionButton.setOnClickListener {
            val intent = Intent(this, FirebaseAddUserActivity::class.java)
            startActivity(intent)
        }
        mainBinding.materialToolbarFirebase.setNavigationOnClickListener {
           finish()
        }

        ItemTouchHelper(object: ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                TODO("Not yet implemented")
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val id = usersAdapter.getUserID(viewHolder.adapterPosition)
                val imageName = usersAdapter.getImageName(viewHolder.adapterPosition)
                val imageRef = storageReference.child("images").child(imageName)

                myReference.child(id).removeValue()
                imageRef.delete()

                Toast.makeText(applicationContext, "The user was deleted!", Toast.LENGTH_SHORT).show()
            }
        }).attachToRecyclerView(mainBinding.recyclerView)

        retrieveDataFromDatabase()
    }

    private fun retrieveDataFromDatabase() {
        myReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                userList.clear()

                for (eachUser in snapshot.children) {
                   val user = eachUser.getValue(Users::class.java)

                    if (user != null) { userList.add(user) }

                    usersAdapter = UsersAdapter(this@FirebaseActivity, userList)
                    mainBinding.recyclerView.layoutManager = LinearLayoutManager(this@FirebaseActivity)
                    mainBinding.recyclerView.adapter = usersAdapter
                }
            }

            override fun onCancelled(error: DatabaseError) { TODO("Not yet implemented") }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.firebase_menu_delete_all, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.deleteAll) {
            showDialogMessage()
        } else if (item.itemId == R.id.signOut) {
            FirebaseAuth.getInstance().signOut()

            val intent = Intent(this@FirebaseActivity, FirebaseLoginActivity::class.java)
            startActivity(intent)
            finish()
        }
        return super.onOptionsItemSelected(item)

    }

    private fun showDialogMessage() {
        val dialogMessage = AlertDialog.Builder(this)
        dialogMessage.setTitle("Delete All Users")
        dialogMessage.setMessage("If click Yes, all will be deleted. If you want to delete a specific user, you can just swipe right or left on that item")

        dialogMessage.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialogInterface, _ ->
            dialogInterface.cancel()
        })

        dialogMessage.setPositiveButton("Yes", DialogInterface.OnClickListener { _, _ ->
            myReference.addValueEventListener(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (eachUser in snapshot.children) {
                        val user = eachUser.getValue(Users::class.java)

                        if (user != null) { imageNameList.add(user.imageName) }
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            })

            myReference.removeValue().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    for (imageName in imageNameList) {
                        val imageRef = storageReference.child("images").child(imageName)
                        imageRef.delete()
                    }
                    usersAdapter.notifyDataSetChanged()
                    Toast.makeText(applicationContext, "All users were deleted", Toast.LENGTH_SHORT).show()
                }
            }
        })
        dialogMessage.create().show()
    }
}