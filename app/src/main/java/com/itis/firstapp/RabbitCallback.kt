package com.itis.firstapp

import android.os.Bundle
import androidx.recyclerview.widget.DiffUtil
import com.itis.firstapp.models.Rabbit

class RabbitCallback: DiffUtil.ItemCallback<Rabbit>() {
    private val KEY_NAME = "NAME"
    private val KEY_BREED = "BREED"

    override fun areItemsTheSame(oldItem: Rabbit, newItem: Rabbit): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Rabbit, newItem: Rabbit): Boolean {
        return oldItem == newItem
    }

    override fun getChangePayload(oldItem: Rabbit, newItem: Rabbit): Any? {

        val diffBundle = Bundle()
        if (oldItem.name != newItem.name) {
            diffBundle.putString(KEY_NAME, newItem.name)
        }
        if (oldItem.breed != newItem.breed) {
        diffBundle.putString(KEY_BREED, newItem.breed)
        }
        return if (diffBundle.isEmpty) null else diffBundle
    }
}
