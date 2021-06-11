package com.foodie.api.data.database

import com.foodie.api.data.database.table.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

object Database {
   fun init() {
       Database.connect("jdbc:h2:./monolithic_db", "org.h2.Driver")

       transaction {
           SchemaUtils.create(Users)
           SchemaUtils.create(Foods)
           SchemaUtils.create(SharedFoods)
           SchemaUtils.create(Friends)
           SchemaUtils.create(RoomParticipants)
       }
   }
}