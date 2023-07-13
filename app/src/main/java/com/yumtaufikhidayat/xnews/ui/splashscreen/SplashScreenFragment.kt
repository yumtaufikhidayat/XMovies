package com.yumtaufikhidayat.xnews.ui.splashscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.yumtaufikhidayat.xnews.R
import com.yumtaufikhidayat.xnews.databinding.FragmentSplashScreenBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

@AndroidEntryPoint
class SplashScreenFragment : Fragment() {

    private var _binding: FragmentSplashScreenBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSplashScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setSplashScreen()
    }

    private fun setSplashScreen() {
        lifecycleScope.launch {
            delay(2.seconds)
            findNavController().apply {
                navigate(R.id.homeFragment)
                popBackStack()
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}