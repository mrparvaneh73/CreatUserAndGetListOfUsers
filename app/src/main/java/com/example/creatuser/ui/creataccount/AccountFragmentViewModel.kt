package com.example.creatuser.ui.creataccount

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.creatuser.data.Repository
import com.example.creatuser.data.local.model.User
import com.example.creatuser.data.remote.model.CreateUser
import com.example.creatuser.data.remote.network.NetworkManager.service
import retrofit2.Call
import retrofit2.Response

class AccountFragmentViewModel(private val repository: Repository):ViewModel() {
    val failed by lazy {
        MutableLiveData(false)
    }

    private val _userId = MutableLiveData<String>()
    val userId : LiveData<String> = _userId

    fun createUser(user: CreateUser):LiveData<String> {

     return   repository.creatuser(user)
//        val call = service.createUser(user)
//        call.enqueue(object : retrofit2.Callback<String> {
//            override fun onResponse(call: Call<String>, response: Response<String>) {
//                _userId.postValue(response.body() )
//            }
//
//            override fun onFailure(call: Call<String>, t: Throwable) {
//                failed.postValue(true)
//                Log.d("response_id",t.message.toString())
//            }
//        })
    }
}