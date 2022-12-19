package com.cleansample.feature.sample.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.cleansample.feature.sample.presentation.detail.SampleDetailSideEffect
import com.cleansample.feature.sample.presentation.detail.SampleDetailState
import com.cleansample.feature.sample.presentation.detail.SampleDetailViewModel
import com.cleansample.feature.sample.ui.databinding.FragmentSampleDetailBinding
import com.cleansample.ui_common.utils.openBrowserIntent
import com.cleansample.ui_common.utils.openGoogleMapsDirectionIntent
import com.cleansample.ui_common.utils.openShareIntent
import com.cleansample.ui_common.utils.toMessage
import dagger.hilt.android.AndroidEntryPoint
import org.orbitmvi.orbit.viewmodel.observe


@AndroidEntryPoint
internal class SampleDetailFragment : Fragment() {


    // This property is only valid between onCreateView and
    // onDestroyView.
    private var _binding: FragmentSampleDetailBinding? = null
    private val binding get() = _binding!!


    private var _sampleDetailController: SampleDetailController? = null
    private val adDetailController get() = _sampleDetailController!!

    private val viewModel: SampleDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentSampleDetailBinding.inflate(inflater, container, false)

        initViews()

        // Start a coroutine in the lifecycle scope
        observeEffects()

        return binding.root
    }

    private fun initViews() {
        _sampleDetailController = SampleDetailController(
            onDocumentClickListener = {
                viewModel.onDocumentClickListenerEvent(it)
            },
            onRetryClickedListener = {
                viewModel.onRetryClickedListenerEvent()
            },
            onCarouselItemClickedListener = {
                viewModel.onCarouselItemClickedListenerEvent(it)

            },
            onShareClickedListener = {
                viewModel.onShareClickedListenerEvent()
            },
            onAddressClickedListener = {
                viewModel.onAddressClickedListenerEvent()
            },
            errorMapper = {
                it.toMessage(resources)
            }
        )
        binding.recyclerView.setController(adDetailController)
    }

    private fun observeEffects() {
        viewModel.observe(
            state = ::render,
            sideEffect = ::handleSideEffect,
            lifecycleOwner = viewLifecycleOwner)
    }

    private fun render(state: SampleDetailState) {
        adDetailController.submitData(state)
    }

    private fun handleSideEffect(sideEffect: SampleDetailSideEffect) {
        when (sideEffect) {
            is SampleDetailSideEffect.OpenDocument -> requireContext().openBrowserIntent(sideEffect.url)
            is SampleDetailSideEffect.OpenShareIntent -> requireContext().openShareIntent(sideEffect.data)
            is SampleDetailSideEffect.OpenPictureScreen -> findNavController().navigate(
                SampleDetailFragmentDirections.actionNavigationSampleDetailToImageViewerBottomSheet(
                    sideEffect.url))
            is SampleDetailSideEffect.OpenMapsDirection -> requireContext().openGoogleMapsDirectionIntent(
                latitude = sideEffect.latitude,
                longitude = sideEffect.longitude)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _sampleDetailController = null
        binding.recyclerView.adapter = null
        _binding = null
    }
}