package com.ekheek.financialinformationproject.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ekheek.financialinformationproject.data.remote.model.Article
import com.ekheek.financialinformationproject.databinding.FragmentHomeBinding
import com.ekheek.financialinformationproject.presentation.home.adapter.CategoryAdapter
import com.ekheek.financialinformationproject.presentation.home.adapter.ItemClickListener
import com.ekheek.financialinformationproject.presentation.home.adapter.NewsAdapter
import com.ekheek.financialinformationproject.util.DataState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val homeViewModel: HomeViewModel by viewModels()

    private val newsAdapter by lazy { NewsAdapter(::onArticleCLick) }

    private lateinit var categoryAdapter: CategoryAdapter
    private var categoryList = mutableListOf<String>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        createCategoryList()
        setupRecyclerView()
        requestApi()
        onCategoryClick()
        return binding.root
    }

    private fun createCategoryList() {
        categoryList.add("business")
        categoryList.add("entertainment")
        categoryList.add("general")
        categoryList.add("health")
        categoryList.add("science")
        categoryList.add("sports")
        categoryList.add("technology")
    }

    private fun setupCategoryRecyclerView() {
        binding.rvCategories.adapter = categoryAdapter
        categoryAdapter.news = categoryList
    }

    private fun onCategoryClick() = binding.rvCategories.apply {
        categoryAdapter = CategoryAdapter(object : ItemClickListener {
            override fun onItemClick(category: String) {
                homeViewModel.category = category
                requestApi()
            }
        })
        setupCategoryRecyclerView()
    }

    private fun setupRecyclerView() {
        binding.rvNews.adapter = newsAdapter
        binding.rvNews.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun requestApi() {
        homeViewModel.getNews()
        lifecycleScope.launch {
            homeViewModel.news.collect {
                when (it) {
                    is DataState.Loading -> {
                        binding.pbNews.visibility = View.VISIBLE
                    }
                    is DataState.Success -> {
                        binding.tvError.visibility = View.INVISIBLE
                        binding.pbNews.visibility = View.INVISIBLE
                        newsAdapter.news = it.data!!.articles
                    }
                    is DataState.Failure -> {
                        binding.pbNews.visibility = View.INVISIBLE
                        binding.tvError.text = it.error
                        binding.tvError.visibility = View.VISIBLE
                    }
                    is DataState.Empty -> {}
                }
            }

        }
    }

    private fun onArticleCLick(article: Article) {
        val action = HomeFragmentDirections.actionHomeFragmentToNewsDetailFragment(article)
        findNavController().navigate(action)
    }
}