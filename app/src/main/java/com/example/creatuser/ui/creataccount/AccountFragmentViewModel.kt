package com.example.creatuser.ui.creataccount

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.creatuser.data.network.NetworkManager.service
import com.example.creatuser.model.User
import retrofit2.Call
import retrofit2.Response

class AccountFragmentViewModel:ViewModel() {
    val failed by lazy {
        MutableLiveData(false)
    }

    private val _userId = MutableLiveData<String>()
    val userId : LiveData<String> = _userId

    fun createUser(user: User) {
        val call = service.createUser(user)
        call.enqueue(object : retrofit2.Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                _userId.postValue(response.body() )
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                failed.postValue(true)
                Log.d("response_id",t.message.toString())
            }
        })
    }
}