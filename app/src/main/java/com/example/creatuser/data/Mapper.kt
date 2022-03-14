package com.example.creatuser.data

import com.example.creatuser.data.local.model.User
import com.example.creatuser.data.remote.model.UserResponse

object Mapper {
    fun transfer(userFromServer: UserResponse): User {
        return User(
            userFromServer._id,
            userFromServer.firstName,
            userFromServer.lastName,
            userFromServer.nationalCode
        )
    }
}