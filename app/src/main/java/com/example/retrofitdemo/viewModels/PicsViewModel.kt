package com.example.retrofitdemo.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofitdemo.models.ImagesResponse
import com.example.retrofitdemo.repository.PicSumRepository
import com.example.retrofitdemo.response.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class PicsViewModel @Inject constructor(
    private val appRepository: PicSumRepository
) : ViewModel() {

    val picsData: MutableLiveData<Resource<ImagesResponse>> = MutableLiveData()

    init {
        getPictures()
    }

    fun getPictures() = viewModelScope.launch {
        fetchPics()
    }


    private suspend fun fetchPics() {
        picsData.postValue(Resource.Loading())
        try {

                val response = appRepository.getPictures()
                picsData.postValue(handlePicsResponse(response))

        } catch (t: Throwable) {
            when (t) {
                is IOException -> picsData.postValue(
                    Resource.Error(
                        "Network Failure"
                    )
                )
                else -> picsData.postValue(
                    Resource.Error(
                        "Conversion Error"
                    )
                )
            }
        }
    }

    private fun handlePicsResponse(response: Response<ImagesResponse>): Resource<ImagesResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }


}