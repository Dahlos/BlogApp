package com.dahlosdev.blogapp.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.dahlosdev.blogapp.R
import com.dahlosdev.blogapp.core.Resource
import com.dahlosdev.blogapp.data.model.Post
import com.dahlosdev.blogapp.data.remote.HomeScreenDataSource
import com.dahlosdev.blogapp.databinding.FragmentHomeScreenBinding
import com.dahlosdev.blogapp.domain.HomeScreenRepoImpl
import com.dahlosdev.blogapp.presentation.HomeScreenViewModel
import com.dahlosdev.blogapp.presentation.HomeScreenViewModelFactory
import com.dahlosdev.blogapp.ui.home.adapter.HomeScreenAdapter
import com.google.firebase.Timestamp


class HomeScreenFragment : Fragment(R.layout.fragment_home_screen) {

    private lateinit var binding: FragmentHomeScreenBinding
    private val viewModel by viewModels<HomeScreenViewModel> { HomeScreenViewModelFactory(HomeScreenRepoImpl(HomeScreenDataSource())) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeScreenBinding.bind(view)

        viewModel.fetchLatestPosts().observe(
            viewLifecycleOwner, Observer { result ->
                when (result) {

                    is Resource.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }

                    is Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        binding.rvHome.adapter = HomeScreenAdapter(result.data)
                    }

                    is Resource.Failure -> {
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(requireContext(), "An Error Occur ${result.exception}", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        )
    }
}