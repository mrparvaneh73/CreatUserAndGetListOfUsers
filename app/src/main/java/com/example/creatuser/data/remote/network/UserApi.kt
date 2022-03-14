package com.example.creatuser.data.remote.network

import com.example.creatuser.data.local.model.User
import com.example.creatuser.data.remote.model.CreateUser
import com.example.creatuser.data.remote.model.UserReqBody
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

interface UserApi {

    @POST("users")
    fun createUser(@Body user: CreateUser) : Call<String>

    @Multipart
    @POST("users/{id}/image") fun uploadImage(@Path("id")id:String, @Part image : MultipartBody.Part): Call<Any>


    @GET("http://papp.ir/api/v1/users") fun getUser(@QueryMap users:HashMap<String,String> = hashMapOf()): Call<List<User>>


    @GET("http://papp.ir/api/v1/users/{id}") fun getShowInfo(@Path("id")id:String):Call<User>
}