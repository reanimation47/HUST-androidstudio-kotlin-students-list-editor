package com.example.hust_example_mobileapp_studentlist_manager

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import vn.edu.hust.listexamples.StudentAdapter
import vn.edu.hust.listexamples.StudentModel

class MainActivity : AppCompatActivity() {
    lateinit var input_name: EditText
    lateinit var input_code: EditText
    lateinit var adapter: StudentAdapter
    var selectedStudent: StudentModel? = null //Keep track of currently selected student for edit
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.layout_mainscreen)

        input_name = findViewById<EditText>(R.id.input_name)
        input_code = findViewById<EditText>(R.id.input_code)

        val studentLists = mutableListOf<StudentModel>()

        adapter = StudentAdapter(studentLists, this)

        val display_student_list = findViewById<ListView>(R.id.display_student_list)
        display_student_list.adapter = adapter

        findViewById<Button>(R.id.btn_add).setOnClickListener{
            if (this.selectedStudent == null)
            {
                val name = input_name.text?.toString()
                val code = input_code.text?.toString()

                if (!(name.isNullOrEmpty() || code.isNullOrEmpty()))
                {
                    val newStudent = StudentModel(name, code)
                    studentLists.add(newStudent)

                    //Reset input fields
                    this.input_name.setText("")
                    this.input_code.setText("")
                    adapter.notifyDataSetChanged()
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
                    this.selectedStudent?.hoten = name
                    this.selectedStudent?.mssv= code

                    //Back to add student mode
                    this.input_name.setText("")
                    this.input_code.setText("")
                    this.selectedStudent?.selected = false

                    //unbind student
                    this.selectedStudent = null
                    this.adapter.notifyDataSetChanged()
                }
            }
        }




//        display_student_list.onItemClickListener = object : AdapterView.OnItemClickListener {
//            override fun onItemClick(
//                parent: AdapterView<*>?,
//                view: View?,
//                position: Int,
//                id: Long
//            ) {
//
//                Log.v("TAG DIFF", "clicked")
//                var clickedStudent = adapter.getItem(position) as StudentModel
//                input_name.setText(clickedStudent.hoten)
//                input_code.setText(clickedStudent.mssv)
//            }
//        }

    }


    fun Student_Selected(student: StudentModel)
    {
        this.selectedStudent?.selected = false

        this.selectedStudent = student

        this.input_name.setText(student.hoten)
        this.input_code.setText(student.mssv)
        this.adapter.notifyDataSetChanged()
    }

    fun Student_Unselected()
    {
        if (this.selectedStudent != null)
        {
            this.input_name.setText("")
            this.input_code.setText("")

            //unbind student
            this.selectedStudent = null
        }
    }

}