package com.example.hust_example_mobileapp_studentlist_manager

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class R_StudentAdapter(val items: List<R_StudentModel>, val listener: R_StudentClickListener? = null): RecyclerView.Adapter<R_StudentAdapter.R_ItemViewHolder>() {

    interface R_StudentClickListener{
        fun ItemClicked(student: R_StudentModel)
    }


    class R_ItemViewHolder(items: List<R_StudentModel>, itemView: View, listener: R_StudentClickListener? = null): RecyclerView.ViewHolder(itemView){
        var name: TextView
        var code: TextView
        init {
            this.name = itemView.findViewById(R.id.text_hoten)
            this.code = itemView.findViewById(R.id.text_mssv)

            itemView.setOnClickListener{
                listener?.ItemClicked(items[adapterPosition])
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): R_StudentAdapter.R_ItemViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.layout_student_item, parent, false)
        return R_ItemViewHolder(items, itemView, listener)
    }

    override fun getItemCount(): Int {
        return  items.size
    }

    override fun onBindViewHolder(holder: R_StudentAdapter.R_ItemViewHolder, position: Int) {
        val item = items[position]
        holder.name.text = item.name
        holder.code.text = item.code
    }
}