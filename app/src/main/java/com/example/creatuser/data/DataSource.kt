package com.example.creatuser.data

import com.example.creatuser.data.local.model.User
import com.example.creatuser.data.remote.model.CreateUser
import com.example.creatuser.data.remote.model.UserResponse
import okhttp3.MultipartBody


interface DataSource {
    fun getUSerList():List<User>
    fun saveUserList(users:List<User>)
    fun creatuser(user:CreateUser):String
    fun uploadimage(id:String,image:MultipartBody.Part)
    fun search(query:HashMap<String,String>):List<User>
    fun showInfo(id: String):User
}