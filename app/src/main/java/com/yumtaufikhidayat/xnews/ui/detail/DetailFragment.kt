package com.yumtaufikhidayat.xnews.ui.detail

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.yumtaufikhidayat.xnews.R
import com.yumtaufikhidayat.xnews.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private var newsUrl: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initToolbarDetail()
        getBundleData()
        showNewsWebView()
    }

    private fun initToolbarDetail() {
        binding.toolbarDetailNews.apply {
            tvToolbarTitle.text = getString(R.string.tvNewsDetail)
            imgBack.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    private fun getBundleData() {
        newsUrl = arguments?.getString(EXTRA_DATA).orEmpty()
    }

    private fun showNewsWebView() {
        binding.apply {
            webViewDetailNews.apply {
                settings.loadsImagesAutomatically = true
                settings.javaScriptEnabled = true
                settings.domStorageEnabled = true
                settings.supportZoom()
                settings.builtInZoomControls = true
                settings.displayZoomControls = true
                scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY
                webViewClient = WebViewClient()
                loadUrl(newsUrl)
                shareNews(newsUrl)
            }
        }
    }

    private fun shareNews(newsUrl: String) {
        binding.toolbarDetailNews.imgShare.setOnClickListener {
            try {
                val body = getString(R.string.tvShareNews, newsUrl)
                val shareIntent = Intent(Intent.ACTION_SEND)
                shareIntent.apply {
                    type = "text/plain"
                    putExtra(Intent.EXTRA_TEXT, body)
                }
                startActivity(
                    Intent.createChooser(
                        shareIntent,
                        getString(R.string.tvShareWith)
                    )
                )
            } catch (e: Exception) {
                Toast.makeText(
                    requireContext(),
                    StringBuilder(getString(R.string.tvOops)).append("\n$e"), Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    companion object {
        const val EXTRA_DATA = "com.yumtaufikhidayat.xnews.ui.detail.EXTRA_DATA"
    }
}