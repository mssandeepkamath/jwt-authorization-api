package com.sandeep.service

import com.sandeep.model.User
import com.sandeep.model.createLoginParam
import com.sandeep.model.createUserParam

interface UserService {
    suspend fun registerUser(params: createUserParam): User?
    suspend fun findUserByEmail(email:String):User?
    suspend fun loginUser(email:String,password:String):User?
}