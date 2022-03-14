package com.example.creatuser.data


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.creatuser.data.local.model.User
import com.example.creatuser.data.remote.model.CreateUser
import okhttp3.MultipartBody
import java.util.concurrent.ExecutorService

class Repository(
    private val serviceExcutor: ExecutorService,
    private val remoteDataSource: DataSource,
    private val localDataSource: DataSource
) {

    fun getUserList(): LiveData<List<User>> {
        val liveData = MutableLiveData<List<User>>()
        serviceExcutor.submit {
            val remoteDataSource: List<User> = remoteDataSource.getUSerList()
            liveData.postValue(remoteDataSource)
           localDataSource.saveUserList(remoteDataSource)
        }

        return liveData
    }
    fun creatuser(user:CreateUser):LiveData<String>{
        val liveData = MutableLiveData<String>()
        serviceExcutor.submit {
           val remoteDatasourceResponce= remoteDataSource.creatuser(user)
            liveData.postValue(remoteDatasourceResponce)

        }
       return liveData
    }
    fun uploadimage(id:String,image:MultipartBody.Part){
        serviceExcutor.submit {
            remoteDataSource.uploadimage(id,image)
        }
    }
    fun searchuser(query: HashMap<String, String>): LiveData<List<User>>{
        val liveData = MutableLiveData<List<User>>()
        serviceExcutor.submit {
            val remoteDataSource: List<User> = remoteDataSource.search(query)
            liveData.postValue(remoteDataSource)
        }
        return liveData

    }

    fun showInfo(id: String):LiveData<User>{
        val liveData=MutableLiveData<User>()
        serviceExcutor.submit {
           val response= remoteDataSource.showInfo(id)
            liveData.postValue(response)
        }
        return liveData
    }
}