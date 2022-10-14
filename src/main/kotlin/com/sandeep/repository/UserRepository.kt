package com.sandeep.repository

import com.sandeep.Util.BaseResponse
import com.sandeep.model.createUserParam

interface UserRepository {
    suspend fun registerUser(params: createUserParam):BaseResponse<Any>
    suspend fun loginUser(email:String,password:String):BaseResponse<Any>
}