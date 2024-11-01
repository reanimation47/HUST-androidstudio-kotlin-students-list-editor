package com.example.hust_example_mobileapp_studentlist_manager

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class R_ImageAdapter(val items: List<R_ItemModel>) {
    class R_ImageViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val imageThumb: ImageView
        val textTitle: TextView
        init {
            imageThumb = itemView.findViewById(R.id.r_view_thumb)
            textTitle = itemView.findViewById(R.id.r_text)
        }

    }
}