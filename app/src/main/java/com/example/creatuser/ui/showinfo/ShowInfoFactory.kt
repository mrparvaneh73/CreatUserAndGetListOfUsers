package com.example.creatuser.ui.showinfo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.creatuser.data.Repository
import com.example.creatuser.ui.users.UsersFragmentViewModel

class ShowInfoFactory(private val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return  if (modelClass.isAssignableFrom(ShowInfoViewModel::class.java)){
            ShowInfoViewModel(repository) as T
        }else{
            modelClass.newInstance()
        }
    }

}
