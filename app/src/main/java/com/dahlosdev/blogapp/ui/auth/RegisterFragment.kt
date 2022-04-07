package com.dahlosdev.blogapp.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.dahlosdev.blogapp.R
import com.dahlosdev.blogapp.core.Result
import com.dahlosdev.blogapp.data.remote.auth.AuthDataSource
import com.dahlosdev.blogapp.databinding.FragmentRegisterBinding
import com.dahlosdev.blogapp.domain.auth.AuthRepoImpl
import com.dahlosdev.blogapp.presentation.auth.AuthViewModel
import com.dahlosdev.blogapp.presentation.auth.AuthViewModelFactory

class RegisterFragment : Fragment(R.layout.fragment_register) {

    private lateinit var binding: FragmentRegisterBinding
    private val viewModel by viewModels<AuthViewModel> { AuthViewModelFactory(AuthRepoImpl(AuthDataSource())) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRegisterBinding.bind(view)
        signUp()
    }

    private fun signUp() {

        binding.btnSignup.setOnClickListener {

            val email = binding.editTextEmail.text.toString().trim()
            val username = binding.editTextUsername.text.toString().trim()
            val password = binding.editTextPassword.text.toString().trim()
            val confirmPassword = binding.editTextConfirmPassword.text.toString().trim()

            if (validateUserData(password, confirmPassword, username, email)) return@setOnClickListener

            createUser(email,password,username)

        }
    }

    private fun createUser(email: String, password: String, username: String) {
        viewModel.signUp(email, password, username).observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Result.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.btnSignup.isEnabled = false
                }
                is Result.Success -> {
                    binding.progressBar.visibility = View.GONE
                    findNavController().navigate(R.id.action_registerFragment_to_setupProfileFragment)
                }
                is Result.Failure -> {
                    binding.progressBar.visibility = View.GONE
                    binding.btnSignup.isEnabled = true
                    Toast.makeText(requireContext(), "An error Occur ${result.exception} ", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun validateUserData(password: String, confirmPassword: String, username: String, email: String): Boolean {

        if (password != confirmPassword) {
            binding.editTextPassword.error = "Password doesn't not match"
            binding.editTextConfirmPassword.error = "Password doesn't not match"
            return true
        }

        if (username.isEmpty()) {
            binding.editTextUsername.error = "Username is empty"
            return true
        }

        if (email.isEmpty()) {
            binding.editTextEmail.error = "Email is empty"
            return true
        }

        if (password.isEmpty()) {
            binding.editTextPassword.error = "Password is empty"
            return true
        }

        if (confirmPassword.isEmpty()) {
            binding.editTextConfirmPassword.error = "Confirm Password is empty"
            return true
        }

        return false
    }
}