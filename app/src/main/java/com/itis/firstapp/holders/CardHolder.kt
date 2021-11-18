package com.itis.firstapp.holders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.itis.firstapp.adapters.PhotoAdapter
import com.itis.firstapp.databinding.ItemCardviewBinding
import com.itis.firstapp.models.RabbitCard

class CardHolder (
    item: View
) : RecyclerView.ViewHolder(item) {

    private var rabbitCard: RabbitCard? = null
    private val binding = ItemCardviewBinding.bind(item)

    fun bind(item: RabbitCard) {
        this.rabbitCard = item
        with(binding) {
            cardTitle.text = item.title
            cardDescription.text = item.description
            photosViewpager.adapter = PhotoAdapter(item.photos)
        }
    }
}
