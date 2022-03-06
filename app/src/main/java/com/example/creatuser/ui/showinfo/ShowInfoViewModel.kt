package com.example.creatuser.ui.showinfo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.creatuser.data.network.NetworkManager
import com.example.creatuser.model.UserDetails
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ShowInfoViewModel:ViewModel() {
    private val _user = MutableLiveData<UserDetails>()
    val user: LiveData<UserDetails> = _user


    fun showDetails(id:String){
        NetworkManager.service.getShowInfo(id).enqueue(object : Callback<UserDetails?> {
            override fun onResponse(
                call: Call<UserDetails?>,
                response: Response<UserDetails?>
            ) {
                _user.postValue(response.body())
            }

            override fun onFailure(call: Call<UserDetails?>, t: Throwable) {

            }
        })
    }


}
