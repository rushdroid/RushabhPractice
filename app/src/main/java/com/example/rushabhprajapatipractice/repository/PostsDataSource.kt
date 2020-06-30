package com.example.rushabhprajapatipractice.repository


import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.example.rushabhprajapatipractice.model.BlogListItem
import com.example.rushabhprajapatipractice.networking.ApiClient
import com.example.rushabhprajapatipractice.networking.ApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class PostsDataSource(coroutineContext: CoroutineContext) :
    PageKeyedDataSource<Int, BlogListItem>() {
    private val apiService = ApiClient.getClient().create(ApiService::class.java)
    private val PAGE_LIMIT = 10;
    private val FIRST_PAGE = 1;
    private val job = Job()
    private val scope = CoroutineScope(coroutineContext + job)

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, BlogListItem>
    ) {
        scope.launch {
            try {
                val response =
                    apiService.fetchBlogs(limit = PAGE_LIMIT, page = FIRST_PAGE)
                when {
                    response.isSuccessful -> {
                        val listing = response.body()
                        val items = listing?.map { it }
                        callback.onResult(items ?: listOf(), null, FIRST_PAGE + 1)
                    }
                }
            } catch (exception: Exception) {
                Log.e("PostsDataSource", "Failed to fetch data!")
            }
        }
    }

    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, BlogListItem>
    ) {
        scope.launch {
            try {
                val response =
                    apiService.fetchBlogs(limit = PAGE_LIMIT, page = params.key)
                when {
                    response.isSuccessful -> {
                        val listing = response.body()
                        val items = listing?.map { it }
                        callback.onResult(items ?: listOf(), params.key + 1)
                    }
                }

            } catch (exception: Exception) {
                Log.e("PostsDataSource", "Failed to fetch data!")
            }
        }

    }

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, BlogListItem>
    ) {
        scope.launch {
            try {
                val response =
                    apiService.fetchBlogs(limit = PAGE_LIMIT, page = params.key)
                when {
                    response.isSuccessful -> {
                        val listing = response.body()
                        val items = listing?.map { it }
                        callback.onResult(items ?: listOf(), params.key - 1)
                    }
                }

            } catch (exception: Exception) {
                Log.e("PostsDataSource", "Failed to fetch data!")
            }
        }

    }

    override fun invalidate() {
        super.invalidate()
        job.cancel()
    }

}