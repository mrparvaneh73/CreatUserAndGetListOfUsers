package com.example.creatuser.ui.uploadimage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.creatuser.data.network.NetworkManager
import okhttp3.MediaType
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UploadImageFragmentViewModel:ViewModel() {
    private val _response = MutableLiveData<String>()
    val response: LiveData<String> = _response
    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun uploadImage(id:String,image:ByteArray){
        val body= MultipartBody.create(MediaType.parse("image/*"),image)
        val request = MultipartBody.Part.createFormData("image","imag.jpg",body)
        NetworkManager.service.uploadImage(id,request).enqueue(object : Callback<Any?> {
            override fun onResponse(call: Call<Any?>, response: Response<Any?>) {
                _response.value=response.body().toString()
            }

            override fun onFailure(call: Call<Any?>, t: Throwable) {
                _error.value=t.message
            }
        })
    }
}