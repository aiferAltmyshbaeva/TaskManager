package com.example.taskmanager.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.taskmanager.App
import com.example.taskmanager.R
import com.example.taskmanager.databinding.FragmentDashboardBinding
import com.example.taskmanager.model.Car
import com.example.taskmanager.utils.showToast
import com.google.firebase.firestore.FirebaseFirestore

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!
    private val db: FirebaseFirestore by lazy {
        FirebaseFirestore.getInstance()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)
        btnSave.setOnClickListener {
            val data = Car(
                brand = etBrand.text.toString(),
                model = etModel.text.toString()
            )
            db.collection(App.auth.currentUser?.uid.toString())
                .add(data).addOnSuccessListener {
                    etBrand.text?.clear()
                    etModel.text?.clear()
                    showToast(getString(R.string.successfully_saved))
                }.addOnFailureListener {
                    showToast(it.message.toString())
                }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}