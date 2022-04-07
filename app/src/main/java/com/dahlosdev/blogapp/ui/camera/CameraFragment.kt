package com.dahlosdev.blogapp.ui.camera

import android.app.Activity.RESULT_OK
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.dahlosdev.blogapp.R
import com.dahlosdev.blogapp.core.Result
import com.dahlosdev.blogapp.data.remote.camera.CameraDataSource
import com.dahlosdev.blogapp.data.remote.home.HomeScreenDataSource
import com.dahlosdev.blogapp.databinding.FragmentCameraBinding
import com.dahlosdev.blogapp.domain.camera.CameraRepoImpl
import com.dahlosdev.blogapp.domain.home.HomeScreenRepoImpl
import com.dahlosdev.blogapp.presentation.HomeScreenViewModel
import com.dahlosdev.blogapp.presentation.HomeScreenViewModelFactory
import com.dahlosdev.blogapp.presentation.camera.CameraViewModel
import com.dahlosdev.blogapp.presentation.camera.CameraViewModelFactory


class CameraFragment : Fragment(R.layout.fragment_camera) {

    private val REQUEST_IMAGE_CAPTURE = 1
    private lateinit var binding: FragmentCameraBinding
    private val viewModel by viewModels<CameraViewModel> { CameraViewModelFactory(CameraRepoImpl(CameraDataSource())) }
    private var bitmap: Bitmap? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCameraBinding.bind(view)

        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        try {
            // TODO CHANGE DEPRECATED FUNCTION startActivityForResult
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(requireContext(), "App for open camera not founded", Toast.LENGTH_SHORT).show()
        }

        binding.btnUploadPhoto.setOnClickListener {
            bitmap?.let {
                viewModel.uploadPhoto(it, binding.etxPhotoDescription.text.toString().trim()).observe(viewLifecycleOwner, Observer { result ->
                    when (result) {
                        is Result.Loading -> {
                            Toast.makeText(requireContext(), "Uploading photo...", Toast.LENGTH_SHORT).show()
                        }
                        is Result.Success -> {
                            findNavController().navigate(R.id.action_cameraFragment_to_homeScreenFragment)
                        }
                        is Result.Failure -> {
                            Toast.makeText(requireContext(), "Error ${result.exception}", Toast.LENGTH_SHORT).show()
                        }
                    }
                })
            }
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            binding.imgAddPhoto.setImageBitmap(imageBitmap)
            bitmap = imageBitmap
        }
    }

}