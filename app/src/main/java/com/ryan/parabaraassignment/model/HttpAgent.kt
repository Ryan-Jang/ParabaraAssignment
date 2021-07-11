package com.ryan.parabaraassignment.model

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

open class HttpAgent {
    companion object {
        private var baseUrl : String = ""
        private var httpClient : OkHttpClient.Builder? = null
        private var retrofitClient : Retrofit? = null
        private var requestService : Any? = null

        fun setUrl(url : String) {
            baseUrl = url;
        }
    }

    private fun getOkHttpClient(): OkHttpClient {
        if (httpClient == null) {
            httpClient = OkHttpClient.Builder()
            httpClient?.addInterceptor(object : Interceptor {
                @Throws(IOException::class)
                override fun intercept(chain: Interceptor.Chain): Response {
                    val requestBuilder : Request.Builder = chain.request().newBuilder()
                        .header("Content-Type", "application/json")
                    val request: Request = requestBuilder.build()
                    return chain.proceed(request)
                }
            })

            httpClient?.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        }

        return httpClient!!.build()
    }

    protected fun getRetrofitInstance(): Retrofit {
        if (retrofitClient == null)
            retrofitClient = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(getOkHttpClient())
                .build()
        return retrofitClient!!
    }

    protected fun <T> getRequestServiceInstance(service : Class<T>): T {
        if (requestService == null)
            requestService = getRetrofitInstance().create(service)
        return requestService!! as T
    }
}