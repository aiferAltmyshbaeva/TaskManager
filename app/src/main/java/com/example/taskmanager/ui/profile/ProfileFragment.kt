package com.example.taskmanager.ui.profile

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.taskmanager.R
import com.example.taskmanager.data.local.Pref
import com.example.taskmanager.databinding.FragmentProfileBinding
import com.example.taskmanager.utils.loadImage
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private val pref: Pref by lazy {
        Pref(requireContext())
    }

    private val launcher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK && result.data != null) {
                val photoUri = result.data?.data
                pref.saveImage(photoUri.toString())
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?): Unit = with(binding) {
        super.onViewCreated(view, savedInstanceState)

        imgExit.setOnClickListener {
            showAlertDialog()
        }

        etName.setText(pref.getName())
        etName.addTextChangedListener {
            pref.saveName(etName.text.toString())
        }

        profileImage.loadImage(pref.getImage())
        profileImage.setOnClickListener {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            launcher.launch(intent)
        }

    }

    private fun showAlertDialog() {
        AlertDialog.Builder(context)
            .setTitle(getString(R.string.exit_profile))
            .setMessage(getString(R.string.are_you_sure))
            .setPositiveButton(getString(R.string.yes)) { dialog, _ ->
//                FirebaseAuth.getInstance().signOut()
                Firebase.auth.signOut()
                findNavController().navigate(
                    R.id.authFragment,
                    null,
                    NavOptions.Builder()
                        .setPopUpTo(R.id.authFragment, false)
                        .build()
                )
            }
            .setNegativeButton(getString(R.string.cancel)) { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

}



