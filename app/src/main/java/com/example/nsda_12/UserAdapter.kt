package com.example.nsda_12

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class UserAdapter(private var users: List<User>) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    // ViewHolder class
    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.userName)
        private val emailTextView: TextView = itemView.findViewById(R.id.userEmail)
        private val phoneTextView: TextView = itemView.findViewById(R.id.userPhone)
        private val addressTextView: TextView = itemView.findViewById(R.id.userAddress)

        fun bind(user: User) {
            // Set user data to the views
            nameTextView.text = user.name
            emailTextView.text = user.email
            phoneTextView.text = user.phone
            addressTextView.text = user.address.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        // Inflate the layout for the user item
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.list_user, parent, false)
        return UserViewHolder(view) // Pass the inflated view to the ViewHolder
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(users[position]) // Bind the user data to the ViewHolder
    }

    override fun getItemCount(): Int = users.size

    // Method to update the list of users and notify changes
    fun updateData(newData: List<User>) {
        users = newData // Update users list with new data
        notifyDataSetChanged() // Notify the RecyclerView of data changes
    }
}



