package com.dahlosdev.blogapp.data.remote

import com.dahlosdev.blogapp.core.Resource
import com.dahlosdev.blogapp.data.model.Post
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class HomeScreenDataSource {

    suspend fun getLatestPosts(): Resource<List<Post>> {
        val postList = mutableListOf<Post>()
        val querySnapshot = FirebaseFirestore.getInstance().collection("posts").get().await()
        for (post in querySnapshot.documents) {
            post.toObject(Post::class.java)?.let { fbPost -> postList.add(fbPost) }
        }
        return Resource.Success(postList)
    }
}