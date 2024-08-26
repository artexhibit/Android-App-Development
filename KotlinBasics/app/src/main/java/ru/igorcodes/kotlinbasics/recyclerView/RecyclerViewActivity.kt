package ru.igorcodes.kotlinbasics.recyclerView
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.igorcodes.kotlinbasics.R

class RecyclerViewActivity: AppCompatActivity() {

    var countryNameList = ArrayList<String>()
    var detailsList = ArrayList<String>()
    var imageList = ArrayList<Int>()

    lateinit var recyclerView: RecyclerView
    lateinit var adapter: RecyclerViewCountriesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_recycler_view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this@RecyclerViewActivity)

        configureDataSource()

        adapter = RecyclerViewCountriesAdapter(countryNameList, detailsList, imageList, this@RecyclerViewActivity)
        recyclerView.adapter = adapter
    }

    private fun configureDataSource() {
        countryNameList.add("United Kingdom")
        countryNameList.add("Germany")
        countryNameList.add("USA")

        detailsList.add("This is United Kingdom flag")
        detailsList.add("This is Germany flag")
        detailsList.add("This is USA flag")

        imageList.add(R.drawable.gb)
        imageList.add(R.drawable.ge)
        imageList.add(R.drawable.usa)
    }
}