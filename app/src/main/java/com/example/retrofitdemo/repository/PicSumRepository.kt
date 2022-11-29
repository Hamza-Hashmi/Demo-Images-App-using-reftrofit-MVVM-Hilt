package com.example.retrofitdemo.repository

import com.example.retrofitdemo.networkCalling.RetrofitInstance

class PicSumRepository {

    suspend fun getPictures() = RetrofitInstance.picsumApi.getPictures()
}