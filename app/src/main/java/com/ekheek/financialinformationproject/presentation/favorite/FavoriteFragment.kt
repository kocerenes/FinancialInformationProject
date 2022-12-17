package com.ekheek.financialinformationproject.presentation.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ekheek.financialinformationproject.data.remote.model.Article
import com.ekheek.financialinformationproject.databinding.FragmentFavoriteBinding
import com.ekheek.financialinformationproject.presentation.favorite.adapter.FavoriteAdapter
import com.google.android.material.snackbar.Snackbar
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
        observeFavoriteNews()
        getFavoriteNews()
        swipeToDelete()
        return binding.root
    }

    private fun swipeToDelete() {
        ItemTouchHelper(object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val favoriteArticle = favoriteAdapter.news[viewHolder.layoutPosition]
                favoriteViewModel.onItemSwiped(favoriteArticle)
            }
        }).attachToRecyclerView(binding.rvNews)
    }

    private fun collectEvents() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            favoriteViewModel.productsEvent.collect { event ->
                when (event) {
                    is NewsEvent.ShowUndoDeleteItemMessage -> {
                        Snackbar.make(
                            requireView(),
                            "Are you sure?",
                            Snackbar.LENGTH_LONG
                        )
                            .setAction("Undo") {
                                favoriteViewModel.onUndoDeleteClick(event.favoriteEntity)
                            }.show()
                    }
                }
            }
        }
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
        collectEvents()
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