package com.dahlosdev.blogapp.domain.home

import com.dahlosdev.blogapp.core.Result
import com.dahlosdev.blogapp.data.model.Post
import kotlinx.coroutines.flow.Flow

interface HomeScreenRepo {
    suspend fun getLatestPosts(): Result<List<Post>>
    suspend fun getLatestPostsFlow(): Flow<Result<List<Post>>>
    suspend fun registerLikeButtonState (postId: String, liked: Boolean)
}