package com.example.rushabhprajapatipractice.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rushabhprajapatipractice.R
import com.example.rushabhprajapatipractice.model.BlogListItem
import com.example.rushabhprajapatipractice.utils.DiffUtilCallBack
import kotlinx.android.synthetic.main.adapter_row.view.*

class BlogPostAdapter :
    PagedListAdapter<BlogListItem, BlogPostAdapter.MyViewHolder>(DiffUtilCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_row, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        getItem(position)?.let { holder.bindPost(it) }
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtID = itemView.txtID
        fun bindPost(blogListItem: BlogListItem) {
            with(blogListItem) {
                txtID.text = blogListItem.id
            }
        }
    }
}