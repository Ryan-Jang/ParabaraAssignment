package com.ryan.parabaraassignment.view

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.ryan.parabaraassignment.R

class ProductViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
    val btnEditRemove: Button = itemView.findViewById(R.id.btnEditRemove)
    val vpContent: ViewPager2 = itemView.findViewById(R.id.vpContent)
    val tvContent: TextView = itemView.findViewById(R.id.tvContent)
}