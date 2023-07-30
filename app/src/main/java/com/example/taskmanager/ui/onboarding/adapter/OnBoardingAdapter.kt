package com.example.taskmanager.ui.onboarding.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.taskmanager.databinding.ItemOnboardingBinding
import com.example.taskmanager.model.OnBoarding
import com.example.taskmanager.utils.loadImage

class OnBoardingAdapter(private val onClick: () -> Unit) :
    RecyclerView.Adapter<OnBoardingAdapter.OnBoardingViewHolder>() {

    private val data = arrayListOf(
        OnBoarding(
            "Remember Daily Tasks",
            "The app provide a platform where all your everyday tasks are displayed",
            "https://cdn.vectorstock.com/images/1000x1000/39/49/22923949.jpg"
        ),
        OnBoarding(
            "Track Progress",
            "You can easily track your daily progress and perform your tasks efficiently",
            "https://cdn.vectorstock.com/images/1000x1000/25/00/23182500.jpg"
        ),
        OnBoarding(
            "Get Notified Instantly",
            "You get notifications of your task and track your daily work on this platform",
            "https://cdn.vectorstock.com/images/1000x1000/33/82/35583382.jpg"
        )
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardingViewHolder {
        return OnBoardingViewHolder(
            ItemOnboardingBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: OnBoardingViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class OnBoardingViewHolder(private val binding: ItemOnboardingBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(onBoarding: OnBoarding) = with(binding) {
            tvTitle.text = onBoarding.title
            tvDesc.text = onBoarding.desc
            btnStart.isVisible = adapterPosition == data.lastIndex
            skip.isVisible = adapterPosition != data.lastIndex
            onBoarding.image?.let { ivBoard.loadImage(it) }

            btnStart.setOnClickListener {
                onClick()
            }
            skip.setOnClickListener {
                onClick()
            }
        }
    }
}