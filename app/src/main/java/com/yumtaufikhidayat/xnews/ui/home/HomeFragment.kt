package com.yumtaufikhidayat.xnews.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.yumtaufikhidayat.xnews.R
import com.yumtaufikhidayat.xnews.databinding.FragmentHomeBinding
import com.yumtaufikhidayat.xnews.ui.LoadMoreAdapter
import com.yumtaufikhidayat.xnews.ui.detail.DetailFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val homeViewModel by viewModels<HomeViewModel>()
    private val topHeadlinesNewsAdapter by lazy { TopHeadlinesNewsAdapter {
        navigateToDetail(it.url.orEmpty())
    } }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setTopHeadlinesNewsAdapter()
        setTopHeadlinesNewsObserver()
    }

    private fun setTopHeadlinesNewsAdapter() {
        binding.rvTopHeadlinesNews.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = topHeadlinesNewsAdapter
        }
    }

    private fun setTopHeadlinesNewsObserver() {
        binding.apply {
            homeViewModel.getTopHeadlinesNews().observe(viewLifecycleOwner) {
                topHeadlinesNewsAdapter.submitData(viewLifecycleOwner.lifecycle, it)
            }

            topHeadlinesNewsAdapter.apply {
                addLoadStateListener { loadState ->
                    pbLoading.isVisible = loadState.source.refresh is LoadState.Loading
                    rvTopHeadlinesNews.isVisible = loadState.source.refresh is LoadState.NotLoading
                    btnRetry.isVisible = loadState.source.refresh is LoadState.Error
                    tvError.isVisible = loadState.source.refresh is LoadState.Error

                    if (loadState.source.refresh is LoadState.NotLoading
                        && loadState.append.endOfPaginationReached
                        && itemCount < 1
                    ) {
                        rvTopHeadlinesNews.isVisible = false
                        tvEmpty.isVisible = true
                    } else {
                        tvEmpty.isVisible = false
                    }

                    btnRetry.setOnClickListener {
                        topHeadlinesNewsAdapter.retry()
                    }
                }

                rvTopHeadlinesNews.adapter = topHeadlinesNewsAdapter.withLoadStateHeaderAndFooter(
                    header = LoadMoreAdapter { topHeadlinesNewsAdapter.retry() },
                    footer = LoadMoreAdapter { topHeadlinesNewsAdapter.retry() }
                )
            }
        }
    }

    private fun navigateToDetail(newsUrl: String) {
        val bundle = Bundle()
        bundle.putString(DetailFragment.EXTRA_DATA, newsUrl)
        findNavController().navigate(R.id.detailFragment, bundle)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}