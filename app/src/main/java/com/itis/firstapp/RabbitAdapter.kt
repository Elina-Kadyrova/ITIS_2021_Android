package com.itis.firstapp

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class RabbitAdapter (
    private val list: List<Rabbit>,
    private val action: (String) -> Unit
) : RecyclerView.Adapter<RabbitHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RabbitHolder = RabbitHolder.create(parent, action)

    override fun onBindViewHolder(holder: RabbitHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
}
