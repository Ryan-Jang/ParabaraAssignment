package com.ryan.parabaraassignment.viewmodel

interface Observer {
    fun notifyUpdate(msg: String, obj: Any)
}