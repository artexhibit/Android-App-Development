package ru.igorcodes.kotlinbasics.firebase
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import ru.igorcodes.kotlinbasics.databinding.FirebaseUsersItemBinding
import ru.igorcodes.kotlinbasics.firebase.UsersAdapter.UsersViewHolder
import java.lang.Exception

class UsersAdapter(var context: Context, var userList: ArrayList<Users>): RecyclerView.Adapter<UsersViewHolder>() {
    inner class UsersViewHolder(val adapterBinding: FirebaseUsersItemBinding): RecyclerView.ViewHolder(adapterBinding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : UsersViewHolder {
        val binding = FirebaseUsersItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UsersViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    fun getUserID(position: Int) : String {
        return userList[position].userID
    }

    fun getImageName(position: Int) : String {
        return userList[position].imageName
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        holder.adapterBinding.textViewFirebaseName.text = userList[position].userName
        holder.adapterBinding.textViewFirebaseAge.text = userList[position].userAge.toString()
        holder.adapterBinding.textViewFirebaseEmail.text = userList[position].userEmail

        val imageURL = userList[position].url
        Picasso.get().load(imageURL).into(holder.adapterBinding.imageViewFirebase, object: Callback {
            override fun onSuccess() {
                holder.adapterBinding.progressBarFirebase.visibility = View.INVISIBLE
            }

            override fun onError(e: Exception?) {
                Toast.makeText(context, e?.localizedMessage, Toast.LENGTH_SHORT).show()
                holder.adapterBinding.progressBarFirebase.visibility = View.VISIBLE
            }
        })

        holder.adapterBinding.linearLayout.setOnClickListener {
            val intent = Intent(context, FirebaseUpdateUserActivity::class.java)
            intent.putExtra("id", userList[position].userID)
            intent.putExtra("name", userList[position].userName)
            intent.putExtra("age", userList[position].userAge)
            intent.putExtra("email", userList[position].userEmail)
            intent.putExtra("imageURL", imageURL)
            intent.putExtra("imageName", userList[position].imageName)

            context.startActivity(intent)
        }
    }
}