package com.sandeep.model

data class User(
    val id:Int,
    val full_name: String,
    val profile: String,
    val email: String,
    var auth_token:String?=null,
    val create_date:String
)




