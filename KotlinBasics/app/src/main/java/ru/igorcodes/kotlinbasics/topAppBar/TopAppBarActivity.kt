package ru.igorcodes.kotlinbasics.topAppBar
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import ru.igorcodes.kotlinbasics.databinding.ActivityTopAppBarBinding

class TopAppBarActivity: AppCompatActivity() {

   // lateinit var toolbar: MaterialToolbar
    lateinit var mainBinding: ActivityTopAppBarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainBinding = ActivityTopAppBarBinding.inflate(layoutInflater)
        val view = mainBinding.root
        setContentView(view)

        mainBinding.buttonOK.setOnClickListener {
            mainBinding.textViewResult.text = mainBinding.editTextName.text.toString()
            Log.d("errorHere", mainBinding.editTextName.toString())
        }

//        toolbar = findViewById(R.id.toolbar)
//        toolbar.overflowIcon = AppCompatResources.getDrawable(this, R.drawable.baseline_more_vert_24_top_app_bar)
//
//        toolbar.setNavigationOnClickListener {
//            Toast.makeText(applicationContext, "Navigation Icon is Clicked", Toast.LENGTH_SHORT).show()
//        }
//
//        toolbar.setOnMenuItemClickListener { item ->
//
//            when(item.itemId) {
//                R.id.share -> Toast.makeText(applicationContext, "Share Icon is Clicked", Toast.LENGTH_SHORT).show()
//                R.id.edit -> Toast.makeText(applicationContext, "Edit Icon is Clicked", Toast.LENGTH_SHORT).show()
//                R.id.settings -> Toast.makeText(applicationContext, "Settings Icon is Clicked", Toast.LENGTH_SHORT).show()
//                else -> Toast.makeText(applicationContext, "Sign Out Icon is Clicked", Toast.LENGTH_SHORT).show()
//            }
//            return@setOnMenuItemClickListener true
//        }
    }
}