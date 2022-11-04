package com.ekheek.financialinformationproject.presentation.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import coil.load
import com.ekheek.financialinformationproject.R
import com.ekheek.financialinformationproject.data.remote.model.Article
import com.ekheek.financialinformationproject.databinding.FragmentNewsDetailBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsDetailFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentNewsDetailBinding? = null

    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!

    private val navigationArgs: NewsDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewsDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindUI(getArticle())
    }

    private fun getArticle() = navigationArgs.article

    private fun bindUI(article: Article) = binding.apply {
        textViewAuthor.text = article.author
        textViewContent.text = article.content
        textViewTitle.text = article.title
        textViewDescription.text = article.description
        textViewPublishedAt.text = article.publishedAt
        imageViewNews.load(article.urlToImage) {
            crossfade(600)
            error(R.drawable.ic_error_placeholder)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}