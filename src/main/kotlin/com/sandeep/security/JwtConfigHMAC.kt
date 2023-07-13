package com.sandeep.security

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm

class JwtConfigHMAC private constructor(secret: String) {

    val algorithm = Algorithm.HMAC256(secret)

    val verifier: JWTVerifier = JWT.require(algorithm).withIssuer(ISSUER).withAudience(AUDIENCE).build()

    fun createAuthToken(id: Int,email:String,name:String): String = JWT.create().withIssuer(ISSUER).withAudience(name).withClaim(CLAIM, id).withClaim(
        EMAIL,email).sign(algorithm)


    companion object {
        private const val ISSUER = "jwt-api-server"
        private const val AUDIENCE = "audience"
        const val CLAIM = "user-id"
        const val EMAIL = "email-id"


        lateinit var instance: JwtConfigHMAC
            private set

        fun initialize(secret: String) {
            synchronized(this)
            {
                if (!this::instance.isInitialized) {
                    instance = JwtConfigHMAC((secret))
                }
            }

        }
    }

}