package com.example.retrofitdemo.networkCalling

import android.provider.SyncStateContract.Constants
import com.example.retrofitdemo.constants.AppConstants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    companion object {

        private val retrofitPicsum by lazy {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()
            Retrofit.Builder()
                .baseUrl(AppConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        }


        val picsumApi by lazy {
            retrofitPicsum.create(API::class.java)
        }
    }
}