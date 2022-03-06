package com.example.creatuser.model

data class UserDetails(
    val _id: String,
    val firstName: String,
    val hobbies: List<String>,
    val image: String,
    val lastName: String,
    val nationalCode: String
)