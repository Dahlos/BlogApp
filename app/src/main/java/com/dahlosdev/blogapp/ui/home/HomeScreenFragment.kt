package com.dahlosdev.blogapp.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.dahlosdev.blogapp.R
import com.dahlosdev.blogapp.data.model.Post
import com.dahlosdev.blogapp.databinding.FragmentHomeScreenBinding
import com.dahlosdev.blogapp.ui.home.adapter.HomeScreenAdapter
import com.google.firebase.Timestamp


class HomeScreenFragment : Fragment(R.layout.fragment_home_screen) {

    private lateinit var binding: FragmentHomeScreenBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeScreenBinding.bind(view)

        val postList = listOf(
            Post(
                "https://cdn.pixabay.com/photo/2020/07/01/12/58/icon-5359553_1280.png",
                "gaston",
                Timestamp.now(),
                "https://cdn.pixabay.com/photo/2020/07/01/12/58/icon-5359553_1280.png"
            ),
            Post(
                "https://cdn.pixabay.com/photo/2020/07/01/12/58/icon-5359553_1280.png",
                "gaston",
                Timestamp.now(),
                "https://cdn.pixabay.com/photo/2020/07/01/12/58/icon-5359553_1280.png"
            ),
            Post(
                "https://cdn.pixabay.com/photo/2020/07/01/12/58/icon-5359553_1280.png",
                "gaston",
                Timestamp.now(),
                "https://cdn.pixabay.com/photo/2020/07/01/12/58/icon-5359553_1280.png"
            )
        )

        binding.rvHome.adapter = HomeScreenAdapter(postList)
    }
}