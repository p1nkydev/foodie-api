package com.foodie.api.data.database

import com.foodie.api.data.database.table.Foods
import com.foodie.api.data.database.table.Friends
import com.foodie.api.data.database.table.SharedFoods
import com.foodie.api.data.database.table.Users
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

object Database {
   fun init() {
       //    Database.connect("jdbc:h2:mem:regular;DB_CLOSE_DELAY=-1;", "org.h2.Driver")

       Database.connect("jdbc:h2:./myh2file", "org.h2.Driver")

       transaction {
           SchemaUtils.create(Users)
           SchemaUtils.create(Foods)
           SchemaUtils.create(SharedFoods)
           SchemaUtils.create(Friends)
       }
   }
}