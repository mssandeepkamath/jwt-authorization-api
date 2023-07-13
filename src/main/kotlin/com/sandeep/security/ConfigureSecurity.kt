package com.sandeep.security

import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.auth.jwt.*

fun Application.configureSecurity()
{
    JwtConfigHMAC.initialize(System.getenv("JWT_SECRET_KEY"))
    install(Authentication)
    {
       jwt {
           verifier(JwtConfigHMAC.instance.verifier)
           validate {
               val claim=it.payload.getClaim(JwtConfigHMAC.CLAIM).asInt()
               if(claim!=null)
               {
                   UserIdPricipalForUser(claim)
               }
               else null
           }
       }

    }
}