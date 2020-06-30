package com.example.rushabhprajapatipractice.networking

import com.example.rushabhprajapatipractice.model.BlogList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("jet2/api/v1/blogs")
    suspend fun fetchBlogs(
        @Query("limit") loadSize: Int = 10,
        @Query("page") after: String? = null
    ): Response<BlogList>
}