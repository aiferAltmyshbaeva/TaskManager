package com.example.taskmanager.ui.task.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.taskmanager.R
import com.example.taskmanager.databinding.ItemTaskBinding
import com.example.taskmanager.model.Task

class TaskAdapter(private val onClick: (Task) -> Unit, private val onLongClick: (Task) -> Unit) :
    RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    private val list = arrayListOf<Task>()
    var mColors = arrayOf("#000000", "#FFFFFF")
    var mColorLength = mColors.size - 1;

    fun addTasks(tasks: List<Task>) {
        list.clear()
        list.addAll(tasks)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(
            ItemTaskBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(list[position])

        holder.itemView.setBackgroundColor(Color.parseColor(mColors[position % 2]))

        val tvTitleText = holder.itemView.findViewById<TextView>(R.id.tv_title)
        tvTitleText.setTextColor(Color.parseColor(mColors[mColorLength - position % 2]))

        val tvDescText = holder.itemView.findViewById<TextView>(R.id.tv_desc)
        tvDescText.setTextColor(Color.parseColor(mColors[mColorLength - position % 2]))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class TaskViewHolder(private val binding: ItemTaskBinding) : ViewHolder(binding.root) {

        fun bind(task: Task) {
            binding.tvTitle.text = task.title
            binding.tvDesc.text = task.desc
            binding.root.setOnClickListener {
                onClick(task)
            }
            itemView.setOnLongClickListener {
                onLongClick(task)
                true
            }
        }
    }
}