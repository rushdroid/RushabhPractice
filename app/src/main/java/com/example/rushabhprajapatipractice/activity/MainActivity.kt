package com.example.rushabhprajapatipractice.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rushabhprajapatipractice.R
import com.example.rushabhprajapatipractice.adapters.BlogPostAdapter
import com.example.rushabhprajapatipractice.vm.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val blogPostAdapter = BlogPostAdapter()
    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //initialize the view model
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        observeLiveData()
        initializeList()
    }

    private fun observeLiveData() {
        //observe live data emitted by view model
        mainViewModel.getPosts().observe(this, Observer {
            Log.d("TAG", "observeLiveData: " + it.size)
            blogPostAdapter.submitList(it)
        })
    }

    private fun initializeList() {
        blogRecyclerView.layoutManager = LinearLayoutManager(this)
        blogRecyclerView.adapter = blogPostAdapter
    }

}