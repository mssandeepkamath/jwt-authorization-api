package com.sandeep.repository

import com.sandeep.Util.BaseResponse
import com.sandeep.security.JwtConfigHMAC
import com.sandeep.service.UserService
import com.sandeep.model.createUserParam

class UserRepositoryImpl(val userService: UserService) : UserRepository {
    override suspend fun registerUser(params: createUserParam): BaseResponse<Any> {

        return if(isUserExists(params.email))
        {
            BaseResponse.ErrorResponse("User is already registered")
        }
        else
        {

            val user=userService.registerUser(params)

            if(user!=null)
            {
                val access_token=JwtConfigHMAC.instance.createAuthToken(user.id,user.email,user.full_name)
                user.auth_token=access_token
                BaseResponse.SuccessResponse(data=user)
            }
            else{
                BaseResponse.ErrorResponse(message="Something went wrong")
            }
        }

    }

    override suspend fun loginUser(email: String, password: String): BaseResponse<Any> {
        val user=userService.loginUser(email,password)
        return if(user!=null)
        {
            val accessToken=JwtConfigHMAC.instance.createAuthToken(user.id,user.email,user.full_name)
            user.auth_token=accessToken
            BaseResponse.SuccessResponse(data=user, message = "Login Successful")
        }
        else{
            BaseResponse.ErrorResponse(message = "wrong credentials,Login unsuccessful!")
        }
    }

    private suspend fun isUserExists(email:String):Boolean{
        return userService.findUserByEmail(email)!=null
    }
}