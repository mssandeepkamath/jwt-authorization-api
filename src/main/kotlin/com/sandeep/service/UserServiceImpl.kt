package com.sandeep.service

import com.sandeep.database.DatabaseFactory.dbQuery
import com.sandeep.database.UserTable
import com.sandeep.model.User
import com.sandeep.model.createLoginParam
import com.sandeep.model.createUserParam
import com.sandeep.security.hash
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.statements.InsertStatement

class UserServiceImpl : UserService {
    override suspend fun registerUser(params: createUserParam): User? {
        var statment:InsertStatement<Number>?=null
       dbQuery {

           statment=UserTable.insert {
               it[full_name]=params.full_name
               it[email]=params.email
               it[password]=hash(params.password)
               it[profile]=params.profile
           }

       }
        return rowToUser(statment?.resultedValues?.get(0))
    }

    override suspend fun findUserByEmail(email: String): User? {
        val user= dbQuery {
            UserTable.select {
                UserTable.email.eq(email)
            }.map { rowToUser(it) }.singleOrNull()

            }
        return user
        }

    override suspend fun loginUser(email:String,password:String): User? {
        val user= dbQuery {
            UserTable.select {
                UserTable.email eq email and  (UserTable.password eq hash(password))
            }.map { rowToUser(it) }.singleOrNull()
        }
        return user
    }


    private fun rowToUser(row:ResultRow?):User?
    {

        return if (row == null)
            null
        else
            User(
                full_name = row[UserTable.full_name],
                email = row[UserTable.email],
                profile = row[UserTable.profile],
                create_date = row[UserTable.create_date].toString(),
                id = row[UserTable.id]
            )
    }

}



