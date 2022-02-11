package com.dahlosdev.blogapp.domain.home

import com.dahlosdev.blogapp.core.Result
import com.dahlosdev.blogapp.data.model.Post

interface HomeScreenRepo {
    suspend fun getLatestPosts(): Result<List<Post>>
}