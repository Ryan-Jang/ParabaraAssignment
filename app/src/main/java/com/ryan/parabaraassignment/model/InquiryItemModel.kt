package com.ryan.parabaraassignment.model

data class InquiryItemModel(
    val page: Int,
    val total: Int,
    val records: Int,
    val rows: ArrayList<ItemModel>
)