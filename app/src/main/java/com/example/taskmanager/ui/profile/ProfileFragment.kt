package com.example.taskmanager.ui.profile

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.taskmanager.R
import com.example.taskmanager.data.local.Pref
import com.example.taskmanager.databinding.FragmentProfileBinding


class ProfileFragment : Fragment() {
//    private val SELECT_PICTURE1 = 100
    private lateinit var binding: FragmentProfileBinding
    private val pref: Pref by lazy {
        Pref(requireContext())
    }

    private val launcher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK && result.data != null) {
                val photoUri = result.data?.data
                pref.saveImage (photoUri.toString())
                binding.profileImage.loadImage(photoUri.toString())
            }
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

        binding.profileImage.loadImage(pref.getImage())

        binding.profileImage.setOnClickListener {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            launcher.launch(intent)
        }

//        binding.profileImage.setOnClickListener {
//            val intent = Intent()
//            intent.type = "image/*"
//            intent.action = Intent.ACTION_PICK
//            startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE1)
//        }


    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (resultCode == RESULT_OK) {
//            if (requestCode === SELECT_PICTURE1) {
//                val selectedImageUri: Uri? = data?.data
//                if (null != selectedImageUri) {
//                    val path = selectedImageUri.path
//                    pref.saveImage(path.toString())
//                    binding.profileImage.setImageURI(selectedImageUri)
//                }
//            }
//        }
//    }
}

private fun ImageView.loadImage(toString: String?) {
    fun ImageView.loadImage(url: String?){
        Glide.with(this).load(url).placeholder(R.drawable.ic_profile).into(this)
    }

}
