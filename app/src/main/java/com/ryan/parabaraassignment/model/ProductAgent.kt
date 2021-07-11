package com.ryan.parabaraassignment.model

import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

class ProductAgent : HttpAgent() {
    init {
        setUrl("https://api.recruit-test.parabara.kr/")
    }

    fun getItemApiService(): ProductService {
        return getRequestServiceInstance(ProductService::class.java)
    }

    interface ProductService {
        @POST("api/product")
        fun addItem(@Body bodyMap: HashMap<String, Any>): Call<AddItemResponse>

        @GET("api/product")
        fun inquiryItem(@Query("page") page: Int, @Query("size") size: Int): Call<InquiryItemResponse>

        @PUT("api/product")
        fun editItem(@Query("id") id: Long, @Query("title") title: String, @Query("content") content: String, @Query("price") price: Long): Call<EditItemResponse>

        @DELETE("api/product/{id}")
        fun removeItem(@Path("id") id: Long): Call<RemoveItemResponse>

        @Multipart
        @POST("api/product/upload")
        fun addImage(@Part("image") image: RequestBody): Call<AddImageResponse>
    }
}