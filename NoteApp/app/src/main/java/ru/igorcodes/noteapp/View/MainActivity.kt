package ru.igorcodes.noteapp.View
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.MaterialToolbar
import ru.igorcodes.noteapp.Adapters.NoteAdapter
import ru.igorcodes.noteapp.Model.Note
import ru.igorcodes.noteapp.NoteApplication
import ru.igorcodes.noteapp.R
import ru.igorcodes.noteapp.ViewModel.NoteViewModel
import ru.igorcodes.noteapp.ViewModel.NoteViewModelFactory

class MainActivity : AppCompatActivity() {

    lateinit var noteViewModel: NoteViewModel
    private lateinit var addActivityResultLauncher: ActivityResultLauncher<Intent>
    private lateinit var updateActivityResultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<MaterialToolbar>(R.id.mainMaterialToolbar)
        setSupportActionBar(toolbar)
        registerActivityResultLauncher()

        window.statusBarColor = ContextCompat.getColor(this, R.color.colorPrimary)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val noteAdapter = NoteAdapter(this)
        recyclerView.adapter = noteAdapter

        val viewModelFactory = NoteViewModelFactory((application as NoteApplication).repository)
        noteViewModel = ViewModelProvider(this, viewModelFactory).get(NoteViewModel::class.java)

        noteViewModel.myAllNotes.observe(this, Observer { notes ->
            //updateUI
            noteAdapter.setNote(notes)
        })

        // Initialize ItemTouchHelper inside onCreate
        ItemTouchHelper(object: ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                noteViewModel.delete(noteAdapter.getNote(viewHolder.adapterPosition))
                Toast.makeText(applicationContext, "Note was deleted", Toast.LENGTH_SHORT).show()
            }
        }).attachToRecyclerView(recyclerView)
    }

    private fun registerActivityResultLauncher() {
        addActivityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult(), ActivityResultCallback { resultAddNote ->
            val resultCode = resultAddNote.resultCode
            val data = resultAddNote.data

            if (resultCode == RESULT_OK && data != null) {
                val noteTitle: String = data.getStringExtra("title").toString()
                val noteDescription: String = data.getStringExtra("description").toString()

                val note = Note(noteTitle, noteDescription)
                noteViewModel.insert(note)
            }
        })

        updateActivityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult(), ActivityResultCallback { resultUpdateNote ->
            val resultCode = resultUpdateNote.resultCode
            val data = resultUpdateNote.data

            if (resultCode == RESULT_OK && data != null) {
                val updatedTitle: String = data.getStringExtra("updatedTitle").toString()
                val updatedDescription: String = data.getStringExtra("updatedDescription").toString()
                val noteID = data.getIntExtra("noteID", -1)

                val newNote = Note(updatedTitle, updatedDescription)
                newNote.id = noteID

                noteViewModel.uodate(newNote)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.new_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.item_add_note -> {
                val intent = Intent(this, NoteAddActivity::class.java)
                addActivityResultLauncher.launch(intent)
            }
            R.id.item_delete_all_notes -> { showDialogMessage() }
        }
        return true
    }

    private fun showDialogMessage() {
        val dialogMessage = AlertDialog.Builder(this)
        dialogMessage.setTitle("Deleter All Notes")
        dialogMessage.setMessage("If clicked Yes, all notes will be deleted. If you want to delete a specific note swipe left or right")
        dialogMessage.setNegativeButton("No", DialogInterface.OnClickListener { dialog, _ ->
            dialog.cancel()
        })

        dialogMessage.setPositiveButton("Yes", DialogInterface.OnClickListener { _, _ ->
            noteViewModel.deleteAllNotes()
        })
        dialogMessage.create().show()
    }
}