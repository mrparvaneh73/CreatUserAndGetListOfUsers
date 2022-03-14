package com.example.creatuser.data.remote.model

data class UserReqBody(
    val _id: String,
    val firstName: String,
    val lastName: String,
    val nationalCode: String,
    val hobbies: List<String>?=null
)