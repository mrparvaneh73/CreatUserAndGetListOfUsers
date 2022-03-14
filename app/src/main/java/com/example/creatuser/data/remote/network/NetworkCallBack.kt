package com.example.creatuser.data.remote.network

interface NetworkCallBack<T> {
    fun onResponse(data:T)
    fun onFailure(t:Throwable)
}