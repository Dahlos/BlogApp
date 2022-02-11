package com.dahlosdev.blogapp.domain

import com.dahlosdev.blogapp.core.Resource
import com.dahlosdev.blogapp.data.model.Post

interface HomeScreenRepo {
    suspend fun getLatestPosts(): Resource<List<Post>>
}