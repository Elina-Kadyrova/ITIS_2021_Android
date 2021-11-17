package com.itis.firstapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.itis.firstapp.R
import com.itis.firstapp.databinding.CardviewPhotoBinding

class PhotoAdapter(
    private val photosList:List<Int>)
    : PagerAdapter() {

    private var binding: CardviewPhotoBinding? = null

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(container.context).inflate(R.layout.cardview_photo,container,false)
        binding = CardviewPhotoBinding.bind(view)
        with(binding){
            this!!.cardPhotoImage.setImageResource(photosList[position])
        }
        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        binding = null
    }

    override fun getCount(): Int {
         return photosList.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
         return view === `object`
    }
}
