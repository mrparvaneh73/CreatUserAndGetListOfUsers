package com.example.creatuser.data.local

import com.example.creatuser.data.DataSource
import com.example.creatuser.data.local.db.UserDao
import com.example.creatuser.data.local.model.User
import com.example.creatuser.data.remote.model.CreateUser
import okhttp3.MultipartBody

class LocalDataSource(private val userDao: UserDao):DataSource {
    override fun getUSerList():List<User> {
return userDao.getAll()
    }

    override fun saveUserList(users: List<User>) {
        userDao.insert(*users.toTypedArray())
    }

    override fun creatuser(user: CreateUser): String {
     return "" //nothing
    }

    override fun uploadimage(id: String, image: MultipartBody.Part) {
//nothing
    }

    override fun search(query: HashMap<String, String>): List<User> {
        return emptyList()
    }

    override fun showInfo(id: String): User {
        return User("","","","")
    }
}