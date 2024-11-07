package com.example.nsda_12

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: UserAdapter
    private val viewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)  // Set the content view without data binding

        // Initialize the RecyclerView and the adapter
        recyclerView = findViewById(R.id.recyclerView)  // Get reference to RecyclerView
        recyclerView.layoutManager= LinearLayoutManager(this)
        adapter = UserAdapter(emptyList())  // Initialize adapter with an empty list
        recyclerView.adapter = adapter  // Set the adapter to the RecyclerView



        val  refreshBtn: FloatingActionButton = findViewById(R.id.refreshBtn)

        refreshBtn.setOnClickListener{

            startActivity(Intent(this@MainActivity,MainActivity::class.java))
            finish()
        }
        // Observe the LiveData for user data
        viewModel.user.observe(this, Observer { user ->
            user?.let {
                adapter.updateData(listOf(user))  // Update the RecyclerView with user data
            }
        })

        // Observe error messages
        viewModel.errorMessage.observe(this, Observer { error ->
            error?.let {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()  // Show error message
            }
        })

        viewModel.fetchUserProfile()  // Trigger data fetching
    }
}



