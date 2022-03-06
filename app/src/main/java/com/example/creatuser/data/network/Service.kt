package com.example.creatuser.data.network

import com.example.creatuser.model.User
import com.example.creatuser.model.UserDetails
import com.example.creatuser.model.UserFromServer
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

interface Service {

    @POST("users")
    fun createUser(
        @Body user: User
    ) : Call<String>

    @Multipart
    @POST("users/{id}/image") fun uploadImage(@Path("id")id:String, @Part image : MultipartBody.Part): Call<Any>


    @GET("http://papp.ir/api/v1/users") fun getUser(@QueryMap users:HashMap<String,String> = hashMapOf()): Call<List<UserFromServer>>


    @GET("http://papp.ir/api/v1/users/{id}") fun getShowInfo(@Path("id")id:String):Call<UserDetails>
}