package com.example.taskmanager.ui.notifications.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.taskmanager.databinding.ItemTaskBinding
import com.example.taskmanager.model.Car

class CarAdapter() : RecyclerView.Adapter<CarAdapter.CarViewHolder>() {
    private val list = arrayListOf<Car>()

    fun addCars(cars: List<Car>) {
        list.clear()
        list.addAll(cars)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        return CarViewHolder(
            ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class CarViewHolder(private val binding: ItemTaskBinding) :
        ViewHolder(binding.root) {
        fun bind(car: Car) {
            binding.tvTitle.text = car.brand
            binding.tvDesc.text = car.model
        }
    }
}