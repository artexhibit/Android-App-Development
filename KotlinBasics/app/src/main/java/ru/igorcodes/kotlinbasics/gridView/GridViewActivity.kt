package ru.igorcodes.kotlinbasics.gridView
import android.os.Bundle
import android.widget.GridView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ru.igorcodes.kotlinbasics.R

class GridViewActivity: AppCompatActivity() {

    lateinit var gridView: GridView

    var nameList = ArrayList<String>()
    var imageList = ArrayList<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_grid_view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        fillArrays()
        configureGridView()
    }

    private fun configureGridView() {
        gridView = findViewById(R.id.gridView)

        var adapter = AnimalsAdapter(this, nameList, imageList)
        gridView.adapter = adapter

        gridView.setOnItemClickListener { adapterView, view, position, id ->
            Toast.makeText(this@GridViewActivity, "You selected ${nameList[position]}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun fillArrays() {
        nameList.add("Bird")
        nameList.add("Cat")
        nameList.add("Chicken")
        nameList.add("Dog")
        nameList.add("Fish")
        nameList.add("Monkey")
        nameList.add("Rabbit")
        nameList.add("Sheep")
        nameList.add("Lion")

        imageList.add(R.drawable.bird)
        imageList.add(R.drawable.cat)
        imageList.add(R.drawable.chicken)
        imageList.add(R.drawable.dog)
        imageList.add(R.drawable.fish)
        imageList.add(R.drawable.monkey)
        imageList.add(R.drawable.rabbit)
        imageList.add(R.drawable.sheep)
        imageList.add(R.drawable.lion)
    }
}