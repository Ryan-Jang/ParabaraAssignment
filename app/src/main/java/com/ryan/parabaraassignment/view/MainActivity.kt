package com.ryan.parabaraassignment.view

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.ryan.parabaraassignment.R
import com.ryan.parabaraassignment.model.BaseResponse
import com.ryan.parabaraassignment.model.ItemModel
import com.ryan.parabaraassignment.viewmodel.InquiryViewModel
import com.ryan.parabaraassignment.viewmodel.Observer

class MainActivity : AppCompatActivity(), Observer {
    private lateinit var rvProduct: RecyclerView
    private lateinit var fbAddProduct: FloatingActionButton
    private lateinit var pbLoading: ProgressBar
    private var productAdapter: ProductAdapter? = null
    private var inquiryViewModel: InquiryViewModel = InquiryViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvProduct = findViewById(R.id.rvProduct)
        fbAddProduct = findViewById(R.id.fbAddProduct)
        pbLoading = findViewById(R.id.pbLoading)

        init()
    }

    override fun onDestroy() {
        super.onDestroy()
        inquiryViewModel.removeObserver(this)
    }

    private fun init() {
        showLoading()
        productAdapter = ProductAdapter()
        rvProduct.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvProduct.adapter = productAdapter

        fbAddProduct.setOnClickListener {

        }

        inquiryViewModel.addObserver(this)
        inquiryViewModel.getInquiryList()
    }

    private fun showLoading() {
        pbLoading.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        pbLoading.visibility = View.GONE
    }

    override fun notifyUpdate(msg: String, obj: Any) {
        hideLoading()

        when(msg) {
            InquiryViewModel.INQUIRY -> productAdapter!!.setList(obj as ArrayList<ItemModel>)
            InquiryViewModel.ERROR -> {
                val toastText: String
                if (obj is BaseResponse)
                    toastText = obj.message!!
                else
                    toastText = obj as String

                Toast.makeText(this, toastText, Toast.LENGTH_SHORT).show()
            }
        }
    }
}