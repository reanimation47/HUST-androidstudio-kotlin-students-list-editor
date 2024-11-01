package com.example.hust_example_mobileapp_studentlist_manager

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class R_ItemAdapter(val items: List<R_ItemModel>, val listener: R_ItemClickListener? = null): RecyclerView.Adapter<R_ItemAdapter.R_ItemViewHolder>() {

    interface R_ItemClickListener{
        fun ItemClicked(position: Int)
    }


    class R_ItemViewHolder(itemView: View, listener: R_ItemClickListener? = null): RecyclerView.ViewHolder(itemView){
        val imageVIew: ImageView
        val textView: TextView
        init {
            this.imageVIew = itemView.findViewById(R.id.r_image)
            this.textView = itemView.findViewById(R.id.r_text)

            itemView.setOnClickListener{
                listener?.ItemClicked(adapterPosition)
            }

        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): R_ItemAdapter.R_ItemViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.layout_r_item, parent, false)
        return R_ItemViewHolder(itemView, listener)
    }

    override fun getItemCount(): Int {
        return  items.size
    }

    override fun onBindViewHolder(holder: R_ItemAdapter.R_ItemViewHolder, position: Int) {
        val item = items[position]
        holder.imageVIew.setImageResource(item.imageResId)
        holder .textView.text = item.title
    }
}