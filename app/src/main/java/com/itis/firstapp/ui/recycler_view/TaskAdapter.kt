package com.itis.firstapp.ui.recycler_view

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.itis.firstapp.model.entities.Task

class TaskAdapter(
    private var list: ArrayList<Task>,
    private var onItemChosenAction: (task: Task) -> Unit,
    private var onItemDeleteAction: (task: Task) -> Unit
    ) : ListAdapter<Task, TaskHolder>(TaskDiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : TaskHolder =
        TaskHolder.create(parent, onItemChosenAction, onItemDeleteAction)

    override fun onBindViewHolder(holder: TaskHolder, position: Int) =
        holder.bind(list[position])

    override fun getItemCount(): Int = list.size

    override fun submitList(list: MutableList<Task>?) {
        super.submitList(
            if (list == null) null
            else ArrayList(list))
    }
}

class TaskDiffUtilCallback : DiffUtil.ItemCallback<Task>() {

    override fun areItemsTheSame(oldItem: Task, newItem: Task) : Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Task, newItem: Task) : Boolean = areItemsTheSame(oldItem, newItem)

}
