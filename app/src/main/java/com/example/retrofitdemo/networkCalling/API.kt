package com.example.retrofitdemo.networkCalling

import com.example.retrofitdemo.models.ImagesResponse
import retrofit2.Response
import retrofit2.http.GET

interface API {

        @GET("v2/list")
        suspend fun getPictures(): Response<ImagesResponse>

}