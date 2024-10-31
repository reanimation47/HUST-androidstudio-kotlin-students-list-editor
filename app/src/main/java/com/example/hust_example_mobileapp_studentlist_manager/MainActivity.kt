package com.example.hust_example_mobileapp_studentlist_manager

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import vn.edu.hust.listexamples.StudentAdapter
import vn.edu.hust.listexamples.StudentModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.layout_mainscreen)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }

        var input_name = findViewById<EditText>(R.id.input_name)
        var input_code = findViewById<EditText>(R.id.input_code)

        val studentLists = mutableListOf<StudentModel>()

        val adapter = StudentAdapter(studentLists)

        val display_student_list = findViewById<ListView>(R.id.display_student_list)
        display_student_list.adapter = adapter

        findViewById<Button>(R.id.btn_add).setOnClickListener{
            val name = input_name.text.toString()
            val code = input_code.text.toString()

            if (!(name.isNullOrEmpty() || code.isNullOrEmpty()))
            {
                val newStudent = StudentModel(name, code)
                studentLists.add(newStudent)
                adapter.notifyDataSetChanged()
            }
        }
        findViewById<Button>(R.id.btn_edit).setOnClickListener{

        }

        display_student_list.onItemClickListener = object : OnItemClickListener{
            override fun onItemClick(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                var clickedStudent = adapter.getItem(position) as StudentModel
                input_name.setText(clickedStudent.hoten)
                input_code.setText(clickedStudent.mssv)
            }

        }


    }
}