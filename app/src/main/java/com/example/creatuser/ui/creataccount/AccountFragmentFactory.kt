package com.example.creatuser.ui.creataccount

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.creatuser.data.Repository


class AccountFragmentFactory(private val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return  if (modelClass.isAssignableFrom(AccountFragmentViewModel::class.java)){
            AccountFragmentViewModel(repository) as T
        }else{
            modelClass.newInstance()
        }
    }

}

