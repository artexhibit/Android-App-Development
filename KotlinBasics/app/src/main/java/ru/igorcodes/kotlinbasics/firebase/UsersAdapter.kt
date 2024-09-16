package ru.igorcodes.kotlinbasics.firebase
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.igorcodes.kotlinbasics.databinding.FirebaseUsersItemBinding
import ru.igorcodes.kotlinbasics.firebase.UsersAdapter.UsersViewHolder

class UsersAdapter(var context: Context, var userList: ArrayList<Users>): RecyclerView.Adapter<UsersViewHolder>() {
    inner class UsersViewHolder(val adapterBinding: FirebaseUsersItemBinding): RecyclerView.ViewHolder(adapterBinding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : UsersViewHolder {
        val binding = FirebaseUsersItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UsersViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        holder.adapterBinding.textViewFirebaseName.text = userList[position].userName
        holder.adapterBinding.textViewFirebaseAge.text = userList[position].userAge.toString()
        holder.adapterBinding.textViewFirebaseEmail.text = userList[position].userEmail
    }
}