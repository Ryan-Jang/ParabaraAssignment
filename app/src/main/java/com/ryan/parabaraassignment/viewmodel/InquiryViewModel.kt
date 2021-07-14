package com.ryan.parabaraassignment.viewmodel

import com.ryan.parabaraassignment.model.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InquiryViewModel {
    companion object {
        const val ERROR = "ERROR"
        const val INQUIRY = "INQUIRY"

        private var totalRecord: Int = -1
        private var page: Int = 1
        private const val size: Int = 50
    }

    private var inquiryAgent: ProductAgent = ProductAgent()

    private var observerList: ArrayList<Observer>? = null

    private var inquiryList: ArrayList<ItemModel> = ArrayList()

    fun addObserver(observer: Observer) {
        if (observerList == null)
            observerList = ArrayList()

        observerList?.apply {
            add(observer)
        }
    }

    fun removeObserver(observer: Observer) {
        observerList?.apply {
            removeAt(indexOf(observer))
        }
    }

    fun getInquiryList() {
        if (totalRecord != -1) {
            page++
            if (page * size >= totalRecord) {
                page--
                return
            }
        }

        inquiryAgent.getItemApiService().inquiryItem(page, size).enqueue(object: Callback<InquiryItemResponse> {
            override fun onResponse(call: Call<InquiryItemResponse>, response: Response<InquiryItemResponse>) {
                if (response.isSuccessful) {
                    totalRecord = response.body()!!.data.total

                    notify(INQUIRY, inquiryList)
                } else
                    notify(ERROR, HttpAgent.getError(response.errorBody()!!))
            }

            override fun onFailure(call: Call<InquiryItemResponse>, t: Throwable) {
                notify(ERROR, "인터넷 연결을 확인해주세요")
            }
        })
    }

    fun notify(msg: String, obj: Any) {
        observerList?.let { arrayList ->
            arrayList.forEach {
                it.notifyUpdate(msg, obj)
            }
        }
    }
}