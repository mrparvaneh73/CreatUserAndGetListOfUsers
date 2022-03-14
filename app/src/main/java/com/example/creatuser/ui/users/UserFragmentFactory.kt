package com.example.creatuser.ui.users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.creatuser.data.Repository


class UserFragmentFactory(private val repository: Repository):ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return  if (modelClass.isAssignableFrom(UsersFragmentViewModel::class.java)){
            UsersFragmentViewModel(repository) as T
        }else{
            modelClass.newInstance()
        }
    }

}
