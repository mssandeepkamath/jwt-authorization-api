package com.sandeep.database

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import java.net.URI

object DatabaseFactory
{
    fun init()
    {
        Database.connect(hikari())
        transaction {
            SchemaUtils.create(UserTable)
        }

    }

//     private fun hikari() : HikariDataSource
//     {

//         val config=HikariConfig()
//         config.driverClassName = System.getenv("JDBC_DRIVER")
// //        config.jdbcUrl = System.getenv("DATABASE_URL")
//         config.maximumPoolSize = 3
//         config.isAutoCommit = false
//         config.transactionIsolation = "TRANSACTION_REPEATABLE_READ"
//         val uri= URI(System.getenv("DATABASE_URL"))
//         val username=uri.userInfo.split(":").toTypedArray()[0]
//         val password=uri.userInfo.split(":").toTypedArray()[1]

//         config.jdbcUrl="jdbc:postgresql://" + uri.host + ":" + uri.port + uri.path + "?sslmode=require" + "&user=$username&password=$password"


//         config.validate()

//         return HikariDataSource(config)
//     }

private fun hikari(): HikariDataSource {
    val config = HikariConfig()
    config.driverClassName = "org.postgresql.Driver"
    config.jdbcUrl = System.getenv("DATABASE_URL")
    config.maximumPoolSize = 3
    config.isAutoCommit = false
    config.transactionIsolation = "TRANSACTION_REPEATABLE_READ"
    
    config.addDataSourceProperty("databaseName", System.getenv("PGDATABASE"))
    config.addDataSourceProperty("serverName", System.getenv("PGHOST"))
    config.addDataSourceProperty("portNumber", System.getenv("PGPORT"))
    config.addDataSourceProperty("user", System.getenv("PGUSER"))
    config.addDataSourceProperty("password", System.getenv("PGPASSWORD"))
    
    config.validate()

    return HikariDataSource(config)
}
    public suspend fun <T> dbQuery(block:()->T):T= withContext(Dispatchers.IO)
    {
        transaction {
            block()
        }
    }

}
