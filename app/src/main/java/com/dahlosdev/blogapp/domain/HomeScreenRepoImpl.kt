package com.dahlosdev.blogapp.domain

import com.dahlosdev.blogapp.core.Resource
import com.dahlosdev.blogapp.data.model.Post

class HomeScreenRepoImpl(private val dataSource: HomeScreenDataSource) : HomeScreenRepo {
    override suspend fun getLatestPosts(): Resource<List<Post>> {
        TODO("Not yet implemented")
    }
}