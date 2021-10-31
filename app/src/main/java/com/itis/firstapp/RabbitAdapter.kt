package com.itis.firstapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class RabbitAdapter (private val list: List<Rabbit>) : RecyclerView.Adapter<RabbitHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RabbitHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_rabbit, parent, false)
        return RabbitHolder(view)
    }

    override fun onBindViewHolder(holder: RabbitHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
}
