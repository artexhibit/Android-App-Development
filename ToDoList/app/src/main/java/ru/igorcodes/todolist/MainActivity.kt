package ru.igorcodes.todolist
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.appbar.MaterialToolbar

class MainActivity : AppCompatActivity() {

    private lateinit var toolbar: MaterialToolbar
    private lateinit var item: EditText
    private lateinit var add: Button
    private lateinit var listView: ListView
    private lateinit var adapter: ArrayAdapter<String>

    private var itemList = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        setOnApplyWindowInsetsListener()

        configureLayoutElements()
        configureDataSource()

        add.setOnClickListener { addButtonPressed() }
        listView.setOnItemClickListener { _, _, index, _ -> itemListViewItemPressed(index) }
    }

    private fun configureLayoutElements() {
        toolbar = findViewById(R.id.toolbar)
        item = findViewById(R.id.editText)
        add = findViewById(R.id.button)
        listView = findViewById(R.id.list)

        toolbar.title = getString(R.string.app_name)
    }

    private fun setOnApplyWindowInsetsListener() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun configureDataSource() {
        itemList = FileHelper.readData(this)
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, android.R.id.text1, itemList)
        listView.adapter = adapter
    }

    private fun addButtonPressed() {
        val itemName: String = item.text.toString()
        itemList.add(itemName)
        item.setText(R.string.empty)
        FileHelper.writeData(itemList, applicationContext)
        adapter.notifyDataSetChanged()
    }

    private fun itemListViewItemPressed(index: Int) {
        val alert = AlertDialog.Builder(this)

        alert.setTitle(R.string.delete)
        alert.setMessage(R.string.delete_message)
        alert.setCancelable(false)

        alert.setNegativeButton(R.string.no) { dialogInterface, _ ->
            dialogInterface.cancel()
        }

        alert.setPositiveButton(R.string.yes) { _, _ ->
            itemList.removeAt(index)
            adapter.notifyDataSetChanged()
            FileHelper.writeData(itemList, applicationContext)
        }
        alert.create().show()
    }
}