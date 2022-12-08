package com.ekheek.financialinformationproject.presentation.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ekheek.financialinformationproject.data.remote.model.Article
import com.ekheek.financialinformationproject.databinding.FragmentFavoriteBinding
import com.ekheek.financialinformationproject.presentation.favorite.adapter.FavoriteAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!
    private val favoriteViewModel: FavoriteViewModel by viewModels()
    private val favoriteAdapter by lazy { FavoriteAdapter(::onArticleCLick) }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        setupRecyclerView()
        getFavoriteNews()
        observeFavoriteNews()
        return binding.root
    }

    private fun getFavoriteNews() {
        favoriteViewModel.getFavoriteNews()
    }

    private fun observeFavoriteNews() = favoriteViewModel.favoriteNews.observe(viewLifecycleOwner) {
        if (it.isNullOrEmpty()) {
            binding.rvNews.visibility = View.GONE
            binding.ivNoData.visibility = View.VISIBLE
            binding.tvNoData.visibility = View.VISIBLE
        } else {
            favoriteAdapter.news = it
        }
    }

    private fun setupRecyclerView() {
        binding.rvNews.adapter = favoriteAdapter
        binding.rvNews.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun onArticleCLick(article: Article) {
        val action = FavoriteFragmentDirections.actionFavoriteFragmentToNewsDetailFragment(article)
        findNavController().navigate(action)
    }
}