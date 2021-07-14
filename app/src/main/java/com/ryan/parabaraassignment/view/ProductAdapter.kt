package com.ryan.parabaraassignment.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ryan.parabaraassignment.R
import com.ryan.parabaraassignment.model.ItemModel

class ProductAdapter: RecyclerView.Adapter<ProductViewHolder>() {
    private var productList: ArrayList<ItemModel>? = null

    fun setList(list: ArrayList<ItemModel>) {
        productList = list;
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.tvTitle.text = productList!![position].title
        holder.btnEditRemove.setOnClickListener {

        }
        holder.vpContent.adapter = null
        holder.tvContent.text = productList!![position].content
    }

    override fun getItemCount(): Int {
        return if (productList == null)
            0
        else
            productList!!.size
    }
}