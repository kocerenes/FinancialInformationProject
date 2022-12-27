package com.ekheek.financialinformationproject.presentation.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.ekheek.financialinformationproject.R
import com.ekheek.financialinformationproject.data.local.entity.FavoriteEntity
import com.ekheek.financialinformationproject.data.remote.model.Article
import com.ekheek.financialinformationproject.databinding.FragmentNewsDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsDetailFragment : Fragment() {

    private var _binding: FragmentNewsDetailBinding? = null

    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!

    private val navigationArgs: NewsDetailFragmentArgs by navArgs()
    private val detailViewModel: NewsDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewsDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindUI(getArticle())
        onClickSeeDetailsTextView()
        clickFavoriteButton()
    }

    private fun onClickSeeDetailsTextView() = binding.textViewGoToWebView.setOnClickListener {
        navigateToWebView(getArticle().url.toString())
    }

    private fun navigateToWebView(url: String) {
        val action = NewsDetailFragmentDirections.actionNewsDetailFragmentToNewsWebViewFragment(url)
        findNavController().navigate(action)
    }

    private fun getArticle() = navigationArgs.article

    private fun bindUI(article: Article) = binding.apply {
        isFav()
        textViewAuthor.text = article.source?.name
        textViewContent.text = article.content
        textViewTitle.text = article.title
        textViewDescription.text = article.description
        textViewPublishedAt.text = article.publishedAt
        tvAuthorDetail.text = "Author(s): " + article.author
        imageViewNews.load(article.urlToImage) {
            crossfade(600)
            error(R.drawable.ic_error_placeholder)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun isFav() {
        detailViewModel.isFav(getArticle())
        detailViewModel.favLiveData.observe(viewLifecycleOwner) {
            if (detailViewModel.favLiveData.value == true) {
                binding.imageButtonFav.setBackgroundResource(R.drawable.ic_bookmark_added)
            } else {
                binding.imageButtonFav.setBackgroundResource(R.drawable.ic_bookmark_outlined)
            }
        }
    }

    private fun clickFavoriteButton() = binding.imageButtonFav.setOnClickListener {
        if (detailViewModel.favLiveData.value == true) {
            detailViewModel.favLiveData.value = false
            detailViewModel.deleteFavoriteNews(getArticle())
            Toast.makeText(
                requireContext(),
                getString(R.string.removed_from_favorite),
                Toast.LENGTH_SHORT
            ).show()
        } else {
            detailViewModel.favLiveData.value = true
            Toast.makeText(
                requireContext(),
                getString(R.string.added_to_favorite),
                Toast.LENGTH_SHORT
            ).show()
            detailViewModel.addFavoriteNews(FavoriteEntity(article = getArticle(), id = 0))
        }
    }
}