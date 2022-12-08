package com.ekheek.financialinformationproject.presentation.webivew

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.ekheek.financialinformationproject.databinding.FragmentNewsWebViewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsWebViewFragment : Fragment() {
    private var _binding: FragmentNewsWebViewBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val navigationArgs: NewsWebViewFragmentArgs by navArgs()

    private fun getNewsUrl() = navigationArgs.url

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewsWebViewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupWebView()
    }

    private fun setupWebView() = binding.webView.apply {
        webViewClient = WebViewClient()
        loadUrl(getNewsUrl())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}