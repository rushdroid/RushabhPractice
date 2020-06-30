package com.example.rushabhprajapatipractice.networking

import com.example.rushabhprajapatipractice.model.BlogList
import com.example.rushabhprajapatipractice.model.BlogListItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("jet2/api/v1/blogs")
    suspend fun fetchBlogs(
        @Query("limit") limit: Int = 10,
        @Query("page") page: Int = 1
    ): Response<List<BlogListItem>>
}