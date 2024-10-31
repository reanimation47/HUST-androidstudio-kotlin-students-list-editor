package vn.edu.hust.listexamples

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckBox
import android.widget.TextView
import com.example.hust_example_mobileapp_studentlist_manager.MainActivity
import com.example.hust_example_mobileapp_studentlist_manager.R

class StudentAdapter(val students: List<StudentModel>, context: Context): BaseAdapter() {
    lateinit var context: Context
    init {
        this.context = context
    }
    override fun getCount(): Int = students.size

    override fun getItem(position: Int): Any = students[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val itemView: View
        val viewHolder: ViewHolder
        if (convertView == null) {
            itemView = LayoutInflater.from(parent?.context).inflate(
                R.layout.layout_student_item,
                parent, false
            )
            viewHolder = ViewHolder(itemView)
            itemView.tag = viewHolder
        } else {
            itemView = convertView
            viewHolder = itemView.tag as ViewHolder
        }

        val student = students[position]
        viewHolder.textHoten.text = student.hoten
        viewHolder.textMssv.text = student.mssv
        viewHolder.checkSelected.isChecked = student.selected

        viewHolder.checkSelected.setOnClickListener {
            Log.v("TAG DIFF", "clicked$position")
            student.selected = viewHolder.checkSelected.isChecked
            if (student.selected)
            {
                (context as MainActivity).Student_Selected(student)
            }else
            {
                (context as MainActivity).Student_Unselected()
            }
//            notifyDataSetChanged()
        }


        return itemView
    }

    class ViewHolder(itemView: View) {
        val textHoten: TextView
        val textMssv: TextView
        val checkSelected: CheckBox
        init {
            textHoten = itemView.findViewById(R.id.text_hoten)
            textMssv = itemView.findViewById(R.id.text_mssv)
            checkSelected = itemView.findViewById(R.id.check_selected)
        }
    }
}