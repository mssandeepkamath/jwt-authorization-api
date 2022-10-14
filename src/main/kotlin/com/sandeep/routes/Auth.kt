package com.sandeep.routes

import com.sandeep.model.createLoginParam
import com.sandeep.repository.UserRepository
import com.sandeep.model.createUserParam
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Application.authRoute(userRepository: UserRepository) {
    routing {
        route("/auth")
        {
            post("/register")
            {
                val params = call.receive<createUserParam>()
                val result = userRepository.registerUser(params)
                call.respond(result.statusCode, result)
            }
            authenticate {
                post("/login")
                {
                    val params = call.receive<createLoginParam>()
                    val result = userRepository.loginUser(params.email, params.password)
                    call.respond(result.statusCode, result)
                }
            }

        }
    }
}