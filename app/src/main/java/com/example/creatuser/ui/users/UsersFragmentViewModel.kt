package com.example.creatuser.ui.users

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.creatuser.data.Repository
import com.example.creatuser.data.local.model.User
import com.example.creatuser.data.remote.model.UserResponse
import com.example.creatuser.data.remote.network.NetworkManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.UnknownServiceException

class UsersFragmentViewModel(private val repository: Repository): ViewModel() {





    fun getUsersFromServer():LiveData<List<User>>{

        return repository.getUserList()

    }
    fun searchFromUsers(firstname: String):LiveData<List<User>>{
            return repository.searchuser(hashMapOf("firstName" to firstname))
    }

}