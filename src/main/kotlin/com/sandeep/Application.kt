package com.sandeep

import com.sandeep.database.DatabaseFactory
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.sandeep.repository.UserRepository
import com.sandeep.repository.UserRepositoryImpl
import com.sandeep.routes.authRoute
import com.sandeep.routes.testRoute
import com.sandeep.security.configureSecurity
import com.sandeep.service.UserService
import com.sandeep.service.UserServiceImpl
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.jackson.*

fun main() {
    embeddedServer(Netty, port = System.getenv("PORT").toInt()) {
        DatabaseFactory.init()
       install(ContentNegotiation)
       {
           jackson()
       }
        configureSecurity()
        val userService:UserService=UserServiceImpl()
        val userRepository:UserRepository=UserRepositoryImpl(userService)
        authRoute(userRepository)
        testRoute()
    }.start(wait = true)
}
