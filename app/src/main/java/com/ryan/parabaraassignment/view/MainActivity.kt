package com.ryan.parabaraassignment.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.ryan.parabaraassignment.R

class MainActivity : AppCompatActivity() {
    private lateinit var rvProduct: RecyclerView
    private lateinit var fbAddProduct: FloatingActionButton
    private var productAdapter: ProductAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvProduct = findViewById(R.id.rvProduct)
        fbAddProduct = findViewById(R.id.fbAddProduct)

        init()
    }

    private fun init() {
        productAdapter = ProductAdapter()
        rvProduct.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvProduct.adapter = productAdapter

        fbAddProduct.setOnClickListener {

        }
    }
}