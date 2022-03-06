package com.example.creatuser.ui.users

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.creatuser.data.network.NetworkManager
import com.example.creatuser.model.UserFromServer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UsersFragmentViewModel:ViewModel() {
    var _listUsers = MutableLiveData<List<UserFromServer>>()

    private val _searchResult = MutableLiveData<List<UserFromServer>>()
    val searchResult: LiveData<List<UserFromServer>> = _searchResult

    fun getUsersFromServer(){
        NetworkManager.service.getUser().enqueue(object : Callback<List<UserFromServer>>{
            override fun onResponse(
                call: Call<List<UserFromServer>>,
                response: Response<List<UserFromServer>>
            ) {
                _listUsers.postValue(response.body())
            }

            override fun onFailure(call: Call<List<UserFromServer>>, t: Throwable) {

            }
        })
    }
    fun searchFromUsers(query:HashMap<String,String>){
        NetworkManager.service.getUser(query).enqueue(object : Callback<List<UserFromServer>?> {
            override fun onResponse(
                call: Call<List<UserFromServer>?>,
                response: Response<List<UserFromServer>?>
            ) {
                _searchResult.postValue(response.body())
            }

            override fun onFailure(call: Call<List<UserFromServer>?>, t: Throwable) {

            }
        })

    }
    fun getUserFromFirstName(firstname:String){
        if (firstname.isNotBlank()){
            searchFromUsers( hashMapOf("firstName" to firstname))
        }


    }
}