package com.ryan.parabaraassignment.model

data class ItemModel(
    val id: Long,
    val title: String,
    val content: String,
    val price: Long,
    val images: Array<String>
)