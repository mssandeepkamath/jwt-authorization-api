package com.sandeep.database

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime

object UserTable: Table("users") {
    val id=integer("id").autoIncrement()
    val full_name=varchar("full_name",256)
    val profile=text("profile")
    val password=text("password")
    val email=varchar("email",256)
    val create_date=datetime("created_at").clientDefault { LocalDateTime.now() }
    override val primaryKey=PrimaryKey(id);
}