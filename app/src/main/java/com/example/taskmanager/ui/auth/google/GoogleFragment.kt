package com.example.taskmanager.ui.auth.google

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.taskmanager.R
import com.example.taskmanager.databinding.FragmentGoogleBinding
import com.example.taskmanager.utils.showToast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider


class GoogleFragment : Fragment() {

    private lateinit var binding: FragmentGoogleBinding

    private val auth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    private lateinit var signInRequest: ActivityResultLauncher<Intent>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGoogleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        signInRequest =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                val signInAccountTask = GoogleSignIn.getSignedInAccountFromIntent(it.data)
                try {
                    val account = signInAccountTask.getResult(ApiException::class.java)
                    if (account != null) {
                        val credential = GoogleAuthProvider.getCredential(
                            "",
                            null
                        )
                        auth.signInWithCredential(credential).addOnCompleteListener {
                            findNavController().navigate(R.id.navigation_home)
                        }.addOnFailureListener {
                            showToast(getString(R.string.any_error))
                        }
                    }
                } catch (e: ApiException) {
                    showToast(getString(R.string.any_error))
                }
            }

        binding.btnSignIn.setOnClickListener {
            val googleSignInOptions = GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()
            val signInClient = GoogleSignIn.getClient(requireActivity(), googleSignInOptions)
            signInRequest.launch(signInClient.signInIntent)
        }
    }
}
