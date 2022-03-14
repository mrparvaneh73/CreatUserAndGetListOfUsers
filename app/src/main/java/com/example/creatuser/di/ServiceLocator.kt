package com.example.creatuser.di

import android.app.Application
import com.example.creatuser.data.Repository
import com.example.creatuser.data.local.LocalDataSource
import com.example.creatuser.data.local.db.AppDataBase
import com.example.creatuser.data.local.db.UserDao_Impl
import com.example.creatuser.data.remote.RemoteDataSource
import com.example.creatuser.data.remote.network.NetworkManager
import com.example.creatuser.data.remote.network.UserApi
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class ServiceLocator( application: Application) {
    private val remoteDataSource=RemoteDataSource(NetworkManager.service)
    private val localDataSource=LocalDataSource(AppDataBase.getDatabase(application).userDao())
   private val excuterService:ExecutorService= Executors.newSingleThreadExecutor()
    val repository=Repository(excuterService,remoteDataSource,localDataSource)
}