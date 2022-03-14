package com.example.creatuser.ui.showinfo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.creatuser.data.Repository
import com.example.creatuser.data.local.model.User
import com.example.creatuser.data.remote.network.NetworkManager
import com.example.creatuser.data.remote.model.UserReqBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ShowInfoViewModel(private val repository: Repository) : ViewModel() {

    fun showDetails(id: String): LiveData<User> {
        return repository.showInfo(id)
    }


}
