package com.example.taskmanager.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.taskmanager.R
import com.example.taskmanager.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val profileImage = binding.imgProfile
        val placeholderImage = R.drawable.ic_default_profile_image
        Glide.with(this).load("")
            .placeholder(placeholderImage)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(profileImage)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}