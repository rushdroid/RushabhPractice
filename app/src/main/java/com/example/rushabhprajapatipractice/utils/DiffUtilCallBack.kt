package com.example.rushabhprajapatipractice.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.rushabhprajapatipractice.model.BlogListItem


class DiffUtilCallBack : DiffUtil.ItemCallback<BlogListItem>() {
    override fun areItemsTheSame(oldItem: BlogListItem, newItem: BlogListItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: BlogListItem, newItem: BlogListItem): Boolean {
        return oldItem.comments == newItem.comments
                && oldItem.content == newItem.content
                && oldItem.id == newItem.id
    }

}