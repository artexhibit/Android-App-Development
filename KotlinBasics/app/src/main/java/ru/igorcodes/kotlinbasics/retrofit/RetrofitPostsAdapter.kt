package ru.igorcodes.kotlinbasics.retrofit
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.igorcodes.kotlinbasics.databinding.RetrofitPostsItemBinding

class RetrofitPostsAdapter(private var postsList: ArrayList<Posts>):
    RecyclerView.Adapter<RetrofitPostsAdapter.PostsViewHolder>() {
    inner class PostsViewHolder(val adapterBinding: RetrofitPostsItemBinding): RecyclerView.ViewHolder(adapterBinding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : PostsViewHolder {
        val binding = RetrofitPostsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostsViewHolder(binding)
    }

    override fun getItemCount() : Int {
        return postsList.size
    }

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        holder.adapterBinding.textViewRetrofitUserID.text = postsList[position].userId.toString()
        holder.adapterBinding.textViewRetrofitID.text = postsList[position].id.toString()
        holder.adapterBinding.textViewRetrofitTitle.text = postsList[position].title
        holder.adapterBinding.textViewRetrofitBody.text = postsList[position].subtitle
    }
}