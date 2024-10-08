package ru.igorcodes.kotlinbasics.retrofit
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.igorcodes.kotlinbasics.R
import ru.igorcodes.kotlinbasics.databinding.ActivityRetrofitMainBinding

class RetrofitMainActivity : AppCompatActivity() {

    private lateinit var retrofitBinding: ActivityRetrofitMainBinding
    private lateinit var adapter: RetrofitPostsAdapter

    private val baseURL = "https://jsonplaceholder.typicode.com/"
    private var postsList = ArrayList<Posts>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        retrofitBinding = ActivityRetrofitMainBinding.inflate(layoutInflater)
        val view = retrofitBinding.root
        setContentView(view)

        retrofitBinding.retrofitRecyclerView.layoutManager = LinearLayoutManager(this)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        showPosts()
    }

    private fun showPosts() {
        val retrofit = Retrofit.Builder().baseUrl(baseURL).addConverterFactory(GsonConverterFactory.create()).build()
        val retrofitAPI: RetrofitAPI = retrofit.create(RetrofitAPI::class.java)
        val call: Call<List<Posts>> = retrofitAPI.getAllPosts()

        call.enqueue(object : Callback<List<Posts>> {
            override fun onResponse(call: Call<List<Posts>>, response: Response<List<Posts>>) {
                if (response.isSuccessful) {
                    retrofitBinding.progressBarRetrofit.isVisible = false
                    retrofitBinding.retrofitRecyclerView.isVisible = true

                    postsList = response.body() as ArrayList<Posts>
                    adapter = RetrofitPostsAdapter(postsList)
                    retrofitBinding.retrofitRecyclerView.adapter = adapter
                }
            }

            override fun onFailure(call: Call<List<Posts>>, e: Throwable) {
                Toast.makeText(applicationContext, e.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        })
    }
}