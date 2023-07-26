package com.example.taskmanager.ui.profile

import android.R.attr
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.taskmanager.data.local.Pref
import com.example.taskmanager.databinding.FragmentProfileBinding


class ProfileFragment : Fragment() {
    private val SELECT_PICTURE1 = 100
    private lateinit var binding: FragmentProfileBinding
    private val pref: Pref by lazy {
        Pref(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.etName.setText(pref.getName())

        binding.etName.setOnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                pref.saveName(binding.etName.text.toString())
            }
        }

        binding.profileImage.setOnClickListener {
            Log.v("ololo", " click");
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_PICK
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE1)

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            if (requestCode === SELECT_PICTURE1) {
                val selectedImageUri : Uri? = data?.data
                if (null != selectedImageUri) {
                    val path = selectedImageUri.path
                    pref.
                    binding.profileImage.setImageURI(selectedImageUri)
                }
            }
        }
    }
}