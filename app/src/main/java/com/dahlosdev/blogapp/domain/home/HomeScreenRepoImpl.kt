package com.dahlosdev.blogapp.domain.home

import com.dahlosdev.blogapp.core.Result
import com.dahlosdev.blogapp.data.model.Post
import com.dahlosdev.blogapp.data.remote.home.HomeScreenDataSource
import kotlinx.coroutines.flow.Flow

class HomeScreenRepoImpl(private val dataSource: HomeScreenDataSource) : HomeScreenRepo {
    override suspend fun getLatestPosts(): Flow<Result<List<Post>>> = dataSource.getLatestPosts()
}