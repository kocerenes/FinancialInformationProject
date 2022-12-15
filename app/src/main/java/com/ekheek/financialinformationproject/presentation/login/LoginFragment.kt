package com.ekheek.financialinformationproject.presentation.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.ekheek.financialinformationproject.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAuth()
        goToRegisterPage()
        onClickLoginButton()
        navigateToHomeFragment()
    }

    private fun getCurrentAuth() = auth.currentUser

    // check if the user already sign in
    private fun isUserLoggedIn(): Boolean {
        return getCurrentAuth() != null
    }

    // if the user signed in, navigate directly to home
    private fun navigateToHomeFragment() {
        if (isUserLoggedIn()) {
            val action = LoginFragmentDirections.actionLoginFragmentToNavigationHome()
            findNavController().navigate(action)
        }
    }

    private fun initAuth() {
        auth = Firebase.auth
    }

    private fun login(view: View) {
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()

        if (email.equals("") || password.equals("")) {
            Toast.makeText(requireContext(), "PLease fill out all the fields!", Toast.LENGTH_SHORT)
                .show()
        } else {
            auth.signInWithEmailAndPassword(email, password).addOnSuccessListener {
                val action = LoginFragmentDirections.actionLoginFragmentToNavigationHome()
                Navigation.findNavController(view).navigate(action)
                Toast.makeText(requireContext(), "Welcome!", Toast.LENGTH_SHORT)
                    .show()
            }.addOnFailureListener { exception ->
                Toast.makeText(requireContext(), exception.localizedMessage, Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun onClickLoginButton() = binding.btnLogin.setOnClickListener {
        login(it)
    }

    private fun goToRegisterPage() = binding.tvGoToRegister.setOnClickListener {
        val action = LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}