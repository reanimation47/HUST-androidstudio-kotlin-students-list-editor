package com.example.hust_example_mobileapp_studentlist_manager

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import vn.edu.hust.listexamples.StudentAdapter
import vn.edu.hust.listexamples.StudentModel

class StudentList_RecycleView : AppCompatActivity() {
    var list_students = mutableListOf<R_StudentModel>()
    lateinit var input_name: EditText
    lateinit var input_code: EditText
    lateinit var adapter: R_StudentAdapter
    var selectedStudent: R_StudentModel? = null //Keep track of currently selected student for edit


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.layout_mainscreen_recycleview)

        input_name = findViewById(R.id.input_name)
        input_code = findViewById(R.id.input_code)

        adapter = R_StudentAdapter(list_students, object: R_StudentAdapter.R_StudentClickListener {
            override fun ItemClicked(student: R_StudentModel) {
                if (selectedStudent != null)
                {
//                    selectedStudent?.selected = false
//                    selectedStudent = student
//                    input_name.setText("")
//                    input_code.setText("")
                }else
                {
                    selectedStudent = student
                    input_name.setText(student.name)
                    input_code.setText(student.code)
                }
            }

        })

        val display_list = findViewById<RecyclerView>(R.id.r_studentlist)
        display_list.adapter = adapter
        display_list.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        findViewById<Button>(R.id.btn_add).setOnClickListener{
            if (this.selectedStudent == null)
            {
                val name = input_name.text?.toString()
                val code = input_code.text?.toString()

                if (!(name.isNullOrEmpty() || code.isNullOrEmpty()))
                {
                    val newStudent = R_StudentModel(name, code)
                    list_students.add(newStudent)

                    //Reset input fields
                    this.input_name.setText("")
                    this.input_code.setText("")
                    adapter.notifyItemInserted(list_students.size-1)
//                    this.adapter.notifyDataSetChanged()
                }
            }
        }


        findViewById<Button>(R.id.btn_edit).setOnClickListener{
            if (this.selectedStudent != null)
            {
                val name = input_name.text?.toString()
                val code = input_code.text?.toString()

                if (!(name.isNullOrEmpty() || code.isNullOrEmpty()))
                {
                    this.selectedStudent?.name= name
                    this.selectedStudent?.code= code

                    //Back to add student mode
                    this.input_name.setText("")
                    this.input_code.setText("")

                    //unbind student
                    this.selectedStudent = null
                    this.adapter.notifyDataSetChanged()
                }
            }
        }

    }
}