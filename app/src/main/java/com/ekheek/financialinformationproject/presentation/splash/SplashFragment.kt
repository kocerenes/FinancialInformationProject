package com.ekheek.financialinformationproject.presentation.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ekheek.financialinformationproject.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : Fragment() {

    private var _binding: FragmentSplashBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        val view = binding.root
        getLooper()
        return view
    }

    private fun getLooper() {
        Handler(Looper.getMainLooper()).postDelayed({
            val action = SplashFragmentDirections.actionNavigationSplashToNavigationLogin()
            findNavController().navigate(action)
        }, 5000)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}