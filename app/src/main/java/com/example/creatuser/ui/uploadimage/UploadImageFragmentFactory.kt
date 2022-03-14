package com.example.creatuser.ui.uploadimage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.creatuser.data.Repository
import com.example.creatuser.ui.users.UsersFragmentViewModel

class UploadImageFragmentFactory(private val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return  if (modelClass.isAssignableFrom(UploadImageFragmentViewModel::class.java)){
            UploadImageFragmentViewModel(repository) as T
        }else{
            modelClass.newInstance()
        }
    }

}

