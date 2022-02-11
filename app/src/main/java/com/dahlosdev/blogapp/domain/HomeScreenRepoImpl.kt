package com.dahlosdev.blogapp.domain

import com.dahlosdev.blogapp.core.Resource
import com.dahlosdev.blogapp.data.model.Post
import com.dahlosdev.blogapp.data.remote.HomeScreenDataSource

class HomeScreenRepoImpl(private val dataSource: HomeScreenDataSource) : HomeScreenRepo {
    override suspend fun getLatestPosts(): Resource<List<Post>> = dataSource.getLatestPosts()
}