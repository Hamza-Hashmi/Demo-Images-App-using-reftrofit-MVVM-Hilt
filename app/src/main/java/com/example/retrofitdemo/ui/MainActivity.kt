package com.example.retrofitdemo.ui


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.retrofitdemo.adapter.ImagesAdapter
import com.example.retrofitdemo.databinding.ActivityMainBinding
import com.example.retrofitdemo.extensions.beGone
import com.example.retrofitdemo.extensions.beVisible
import com.example.retrofitdemo.extensions.showMessage
import com.example.retrofitdemo.response.Resource
import com.example.retrofitdemo.viewModels.PicsViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    private val viewModel : PicsViewModel by viewModels()
    @Inject
    lateinit var imagesAdapter :ImagesAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.rootLayout)
        getPictures()
    }


    private fun getPictures() {
        viewModel.picsData.observe(this, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    binding.progressbar.beGone()
                    response.data?.let { picsResponse ->
                        imagesAdapter.differ.submitList(picsResponse)
                        /*imagesAdapter.adapter = ImagesAdapter*/
                        binding.rvPics.adapter = imagesAdapter
                    }
                }

                is Resource.Error -> {
                    binding.progressbar.beGone()
                    response.message?.let { message ->
                        showMessage(message)
                    }

                }

                is Resource.Loading -> {
                    binding.progressbar.beVisible()
                }
            }
        })
    }
}