package com.example.creatuser.data.remote

import com.example.creatuser.data.DataSource
import com.example.creatuser.data.Mapper
import com.example.creatuser.data.local.model.User
import com.example.creatuser.data.remote.model.CreateUser
import com.example.creatuser.data.remote.model.UserResponse
import com.example.creatuser.data.remote.network.UserApi
import okhttp3.MultipartBody


class RemoteDataSource( private val service:UserApi ):DataSource {
    override fun getUSerList():List<User> {
       val result:List<User> = service.getUser().execute().body() ?: emptyList()
        return   result
    }

    override fun saveUserList(users: List<User>) {
        //nothing
    }

    override fun creatuser(user: CreateUser):String {

       return service.createUser(user).execute().body() ?: ""
    }

    override fun uploadimage(id: String, image: MultipartBody.Part) {
        service.uploadImage(id,image).execute().body()
    }

    override fun search(query: HashMap<String, String>): List<User> {
      return  service.getUser(query).execute().body() ?: emptyList()
    }

    override fun showInfo(id: String): User {
        return service.getShowInfo(id).execute().body() ?: User("","","","")
    }
}