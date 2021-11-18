package com.itis.firstapp.adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.itis.firstapp.R
import com.itis.firstapp.RabbitCallback
import com.itis.firstapp.holders.RabbitHolder
import com.itis.firstapp.models.Rabbit

class RabbitAdapter (
    private val onDeleteItemAction: (Rabbit) -> Unit,
) :  ListAdapter<Rabbit, RabbitHolder>(RabbitCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RabbitHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_rabbit, parent, false)
        return RabbitHolder(view, onDeleteItemAction)
    }


    override fun onBindViewHolder(holder: RabbitHolder, position: Int, payloads: MutableList<Any>) {
        if (payloads.isEmpty())
            super.onBindViewHolder(holder, position, payloads)
        else {
            payloads.last().takeIf { it is Bundle }?.let {
                holder.updateFields(it as Bundle)
            }
        }
    }

    override fun onBindViewHolder(holder: RabbitHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun submitList(list: MutableList<Rabbit>?) {
        super.submitList(
            if (list == null) null
            else ArrayList(list)
        )
    }
}
