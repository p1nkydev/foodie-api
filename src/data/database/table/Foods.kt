package com.foodie.api.data.database.table

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

object Foods : Table() {
    val id: Column<Long> = long("id").autoIncrement()
    val name = text("name")
    val description = text("description")
    override val primaryKey = PrimaryKey(id)
}