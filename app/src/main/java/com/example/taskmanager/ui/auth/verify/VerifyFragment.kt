package com.example.taskmanager.ui.auth.verify

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.taskmanager.R
import com.example.taskmanager.ui.auth.phone.PhoneFragment.Companion.VERIFY_KEY
import com.example.taskmanager.databinding.FragmentVerifyBinding
import com.example.taskmanager.utils.showToast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider


class VerifyFragment : Fragment() {

    private lateinit var binding: FragmentVerifyBinding
    private val auth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVerifyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val verId = arguments?.getString(VERIFY_KEY)

        binding.btnAccept.setOnClickListener{
            val credential = PhoneAuthProvider.getCredential(verId!!, binding.etCode.text.toString())
            signInWithCred(credential)
        }
    }

    private fun signInWithCred(credential: PhoneAuthCredential){
        auth.signInWithCredential(credential).addOnSuccessListener {
            findNavController().navigate(R.id.navigation_home)
        }.addOnFailureListener {
            showToast(getString(R.string.any_error))
        }
    }

}