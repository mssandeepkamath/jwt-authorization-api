package com.sandeep.routes

import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.response.*
import io.ktor.routing.*

fun Application.testRoute()
{
    routing {

   authenticate {

       get("/test")
       {
           call.respond("Hi bro")
       }

   }


    }

}