package com.example.rushabhprajapatipractice.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rushabhprajapatipractice.R
import com.example.rushabhprajapatipractice.model.BlogListItem
import com.example.rushabhprajapatipractice.utils.DiffUtilCallBack
import com.example.rushabhprajapatipractice.utils.Util
import kotlinx.android.synthetic.main.adapter_row.view.*

class BlogPostAdapter() :
    PagedListAdapter<BlogListItem, BlogPostAdapter.MyViewHolder>(DiffUtilCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_row, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        getItem(position)?.let { holder.bindPost(it) }
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtUsername = itemView.txtUsername
        val txtDesignnation = itemView.txtUserDesignation
        val txtTime = itemView.txtTime
        val txtArticleDetail = itemView.txtArticleDetail
        val txtArticleTitle = itemView.txtArticleTitle
        val txtArticleUrl = itemView.txtArticleURL
        val txtLike = itemView.txtLikes
        val txtComment = itemView.txtComments
        val imgProfile = itemView.imgProfilePic
        val imgArticleFeed = itemView.imgArticleImage
        fun bindPost(blogListItem: BlogListItem) {
            with(blogListItem) {
                txtUsername.text = blogListItem.user.get(0).name
                txtDesignnation.text = blogListItem.user.get(0).designation
                txtTime.text = Util.covertTimeToText(blogListItem.createdAt)
                txtArticleDetail.text = blogListItem.content
                Glide.with(itemView).load(blogListItem.user.get(0).avatar).into(imgProfile)
                if (blogListItem.media.size > 0){
                    txtArticleTitle.text = blogListItem.media.get(0).title
                    txtArticleUrl.text = blogListItem.media.get(0).url
                    Glide.with(itemView).load(blogListItem.media.get(0).image).into(imgArticleFeed)
                }else{
                    txtArticleTitle.visibility = View.GONE
                    txtArticleUrl.visibility = View.GONE
                }
                txtLike.text = Util.withSuffix(blogListItem.likes.toLong()) + " Likes"
                txtComment.text = Util.withSuffix(blogListItem.comments.toLong()) + " Comments"
            }
        }
    }
}