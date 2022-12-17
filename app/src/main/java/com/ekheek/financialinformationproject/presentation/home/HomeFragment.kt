package com.ekheek.financialinformationproject.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ekheek.financialinformationproject.R
import com.ekheek.financialinformationproject.data.remote.model.Article
import com.ekheek.financialinformationproject.databinding.FragmentHomeBinding
import com.ekheek.financialinformationproject.presentation.home.adapter.CategoryAdapter
import com.ekheek.financialinformationproject.presentation.home.adapter.ItemClickListener
import com.ekheek.financialinformationproject.presentation.home.adapter.NewsAdapter
import com.ekheek.financialinformationproject.util.DataState
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
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

    private lateinit var auth: FirebaseAuth

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
        setupSearchView()
        onSwipeRefresh()
        auth = Firebase.auth // initialize Firebase auth
        onLogOutIconClick()
        return binding.root
    }

    private fun onSwipeRefresh() {
        with(binding) {
            swiperefresh.setOnRefreshListener {
                requestApi()
                swiperefresh.isRefreshing = false
            }
        }
    }

    private fun onLogOutIconClick() = binding.ivLogOut.setOnClickListener {
        showAlertDialog()
    }

    // Confirm deletion alert dialog
    private fun showAlertDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setMessage(R.string.log_out_question)
            .setPositiveButton(getString(R.string.yes)) { _, _ ->
                auth.signOut()
                Toast.makeText(
                    requireContext(),
                    R.string.logged_out_successfully,
                    Toast.LENGTH_LONG
                ).show()
                findNavController().navigate(R.id.action_navigation_home_to_navigation_login)
            }.setNegativeButton(getString(R.string.no)) { _, _ ->

            }
            .show()
    }

    private fun setupSearchView() = binding.searchView.setOnQueryTextListener(object :
        SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            if (query != null) {
                searchNews(query)
            }
            return false
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            return false
        }
    })

    private fun searchNews(searchText: String) {
        homeViewModel.searchNews(searchText)
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