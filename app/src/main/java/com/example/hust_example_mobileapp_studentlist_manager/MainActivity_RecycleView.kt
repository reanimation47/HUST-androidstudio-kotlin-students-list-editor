package com.example.hust_example_mobileapp_studentlist_manager

import android.icu.text.Transliterator.Position
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

class MainActivity_RecycleView : AppCompatActivity() {
    var items = mutableListOf<R_ItemModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main_recycle_view)

        for (i in 0 .. 100)
        {
            this.items.add(
                R_ItemModel(
                "Item $i",
                    R.drawable.baseline_bedtime_24
//                resources.getIdentifier("thumb$i", "drawable", packageName)
            )
            )
        }

        val adapter = R_ItemAdapter(items, object: R_ItemAdapter.R_ItemClickListener {
            override fun ItemClicked(position: Int) {
                Log.v("TAG DIFF", "clicked $position")
            }

        })

        val recycleView = findViewById<RecyclerView>(R.id.r_view)
        recycleView.adapter =adapter

        recycleView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        //recycleView.layoutManager = GridLayoutManager(this, 3)
        //recycleView.layoutManager = GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false)
        //recycleView.layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)

        findViewById<Button>(R.id.r_btn_add).setOnClickListener{
            items.add(1,R_ItemModel("NEW ITEM", R.drawable.baseline_bedtime_24))
            adapter.notifyItemInserted(1)
        }

        findViewById<Button>(R.id.r_btn_delete).setOnClickListener{
            items.removeAt(1)
            adapter.notifyItemRemoved(1)
        }

        findViewById<Button>(R.id.r_btn_update).setOnClickListener{
            items[1].title = "updated"
            adapter.notifyItemChanged(1)
        }

    }

}