package ru.igorcodes.photoalbum.view
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.igorcodes.photoalbum.R
import ru.igorcodes.photoalbum.adapter.MyImagesAdapter
import ru.igorcodes.photoalbum.databinding.ActivityMainBinding
import ru.igorcodes.photoalbum.viewModel.MyImagesViewModel

class MainActivity: AppCompatActivity() {

    lateinit var myImagesViewModel: MyImagesViewModel
    lateinit var mainBinding: ActivityMainBinding
    lateinit var myImagesAdapter: MyImagesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(mainBinding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        myImagesViewModel = ViewModelProvider(this)[MyImagesViewModel::class.java]

        mainBinding.recyclerView.layoutManager = LinearLayoutManager(this)
        myImagesAdapter = MyImagesAdapter(this)
        mainBinding.recyclerView.adapter = myImagesAdapter

        myImagesViewModel.getAllImages().observe(this, Observer { images ->
            //updateUI
            myImagesAdapter.setImage(images)
        })

        mainBinding.floatingActionButton.setOnClickListener {
            val intent = Intent(this, AddImageActivity::class.java)
            startActivity(intent)
        }

        ItemTouchHelper(object: ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                return TODO("Provide the return value")
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
               myImagesViewModel.delete(myImagesAdapter.returnItemAtPosition(viewHolder.adapterPosition))
            }
        }).attachToRecyclerView(mainBinding.recyclerView)
    }
}