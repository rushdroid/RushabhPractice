package com.example.rushabhprajapatipractice.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.rushabhprajapatipractice.model.BlogListItem
import com.example.rushabhprajapatipractice.repository.PostsDataSource
import kotlinx.coroutines.Dispatchers

class MainViewModel : ViewModel() {

    var postsLiveData: LiveData<PagedList<BlogListItem>>

    init {
        val config = PagedList.Config.Builder()
            .setPageSize(30)
            .setEnablePlaceholders(false)
            .build()
        postsLiveData = initializedPagedListBuilder(config).build()
    }

    fun getPosts(): LiveData<PagedList<BlogListItem>> = postsLiveData

    private fun initializedPagedListBuilder(config: PagedList.Config):
            LivePagedListBuilder<Int, BlogListItem> {

        val dataSourceFactory = object : DataSource.Factory<Int, BlogListItem>() {
            override fun create(): DataSource<Int, BlogListItem> {
                return PostsDataSource(Dispatchers.IO)
            }
        }
        return LivePagedListBuilder<Int, BlogListItem>(dataSourceFactory, config)
    }

}