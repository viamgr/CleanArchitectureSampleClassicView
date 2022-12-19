package com.cleansample.ui_common.components.image_viewer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.cleansample.ui_common.databinding.FragmentImageViewerBinding


class ImageViewerFragment : Fragment() {
    private val args: ImageViewerFragmentArgs by navArgs()

    private var _binding: FragmentImageViewerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentImageViewerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.url = args.url
        binding.close.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.close.z = Int.MAX_VALUE.toFloat()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
