package com.example.tasklist.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tasklist.data.Tarea
import com.example.tasklist.databinding.ItemTareaBinding

class TareaAdapter (
    private var dataSet: List<Tarea> = emptyList()
    private val onItemClickListener: (Int) -> Unit,
    private val onItemDeleteClickListener: (Int) -> Unit,
    private val onItemCheckedClickListener: (Int) -> Unit
    ) : RecyclerView.Adapter<TaskViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
            val binding = ItemTareaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return TaskViewHolder(binding)
        }

        override fun getItemCount(): Int = dataSet.size

        override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
            holder.render(dataSet[position])
            holder.itemView.setOnClickListener {
                onItemClickListener(position)
            }
            holder.binding.deleteButton.setOnClickListener {
                onItemDeleteClickListener(position)
            }
            holder.binding.doneCheckBox.setOnCheckedChangeListener { checkbox, _ ->
                if (checkbox.isPressed) {
                    onItemCheckedClickListener(position)
                }
            }
        }

        fun updateData(dataSet: List<Tarea>) {
            this.dataSet = dataSet
            notifyDataSetChanged()
        }
    }

    class TaskViewHolder(val binding: ItemTareaBinding) : RecyclerView.ViewHolder(binding.root) {

        fun render(task: Tarea) {
            binding.nameTextView.text = task.name
            binding.doneCheckBox.isChecked = task.done
        }

}