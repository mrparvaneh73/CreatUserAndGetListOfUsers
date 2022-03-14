package com.example.creatuser.data.local.db

import androidx.room.*
import com.example.creatuser.data.local.model.User

@Dao
interface UserDao {

    @Query("SELECT * FROM user ORDER BY firstName ASC")
    fun getAll(): List<User>

    @Insert
   fun insert(vararg user: User)
    @Update
    fun updateUser(user: User)

    @Delete
   fun delete(user: User)
}