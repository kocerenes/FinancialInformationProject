package com.ekheek.financialinformationproject.presentation.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.ekheek.financialinformationproject.R
import com.ekheek.financialinformationproject.databinding.FragmentRegisterBinding
import com.ekheek.financialinformationproject.util.Constants
import com.ekheek.financialinformationproject.util.loadImage
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAuth()
        goToLoginPage()
        onClickRegisterButton()
        getImages()
        navigateToHomeFragment()
    }

    private fun getImages() = binding.apply {
        viewAppNameRegister.loadImage(Constants.Images.logo)
        viewRegister.loadImage(Constants.Images.registerPNG)
    }

    private fun getCurrentAuth() = auth.currentUser

    private fun isUserLoggedIn(): Boolean {
        return getCurrentAuth() != null
    }

    private fun navigateToHomeFragment() {
        if (isUserLoggedIn()) {
            val action = RegisterFragmentDirections.actionRegisterFragmentToNavigationHome()
            findNavController().navigate(action)
        }
    }

    private fun initAuth() {
        auth = Firebase.auth
    }

    private fun onClickRegisterButton() = binding.btnRegister.setOnClickListener {
        signUp(it)
    }

    private fun goToLoginPage() = binding.tvGoToLogin.setOnClickListener {
        val action = RegisterFragmentDirections.actionRegisterFragmentToLoginFragment()
        findNavController().navigate(action)
    }

    private fun signUp(view: View) = binding.apply {
        val email = etEmailRegister.text.toString()
        val password = etPasswordRegister.text.toString()

        if (email == "" || password == "") {
            Toast.makeText(
                requireContext(),
                getString(R.string.fill_all_fields),
                Toast.LENGTH_SHORT
            )
                .show()
        } else {
            auth.createUserWithEmailAndPassword(email, password).addOnSuccessListener {
                val action = RegisterFragmentDirections.actionRegisterFragmentToNavigationHome()
                Navigation.findNavController(view).navigate(action)
                Toast.makeText(
                    requireContext(),
                    getString(R.string.account_created),
                    Toast.LENGTH_SHORT
                )
                    .show()
            }.addOnFailureListener { exception ->
                Toast.makeText(requireContext(), exception.localizedMessage, Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}