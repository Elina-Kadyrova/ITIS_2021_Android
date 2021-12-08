package com.itis.firstapp.rv

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.itis.firstapp.databinding.TrackItemBinding
import com.itis.firstapp.models.Track

class TrackHolder(
    private val binding: TrackItemBinding,
    private val itemClick: (Int) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Track) {
        with(binding) {
            tvTitle.text = item.title
            tvAuthor.text = item.author
            ivCover.setImageResource(item.cover)
        }
        itemView.setOnClickListener {
            itemClick(item.id)
        }
    }

    companion object {
        fun create (
            parent: ViewGroup,
            itemClick: (Int) -> Unit
        ) = TrackHolder (
            TrackItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            itemClick)
    }
}
