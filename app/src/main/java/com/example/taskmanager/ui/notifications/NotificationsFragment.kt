package com.example.taskmanager.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.taskmanager.App
import com.example.taskmanager.databinding.FragmentNotificationsBinding
import com.example.taskmanager.model.Car
import com.example.taskmanager.ui.notifications.adapter.CarAdapter
import com.example.taskmanager.utils.showToast
import com.google.firebase.firestore.FirebaseFirestore

class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null
    private val binding get() = _binding!!
    private val db: FirebaseFirestore by lazy {
        FirebaseFirestore.getInstance()
    }

    private val adapter = CarAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?): Unit = with(binding) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.adapter = adapter
        db.collection(App.auth.currentUser?.uid.toString())
            .get().addOnSuccessListener {
                val data = it.toObjects(Car::class.java)
                adapter.addCars(data)
            }.addOnFailureListener {
                showToast(it.message.toString())
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}