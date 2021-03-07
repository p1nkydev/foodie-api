package com.foodie.api.data.database.table

import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table

object SharedFoods : Table() {
    val ownerId = long("ownerId")
    val foodId = long("foodId")
}

object Rooms : Table() {
    val id = long("id").autoIncrement()
    val ownerId = long("ownerId")
    val name = text("name")

    override val primaryKey = PrimaryKey(id)
}

object RoomParticipants : Table() {
    val roomId = reference("roomId", Rooms.id, onDelete = ReferenceOption.CASCADE)
    val participantId = long("participantId")

    override val primaryKey = PrimaryKey(roomId, participantId)
}

object RoomFoodProperties : Table() {
    val roomId = reference("roomId", Rooms.id, onDelete = ReferenceOption.CASCADE)
    val foodPropertyId = reference("propertyId", FoodProperties.id, onDelete = ReferenceOption.CASCADE)

    override val primaryKey = PrimaryKey(roomId, foodPropertyId)
}

object FoodProperties : Table() {
    val id = long("id").autoIncrement()
    val foodId = long("foodId")
    val sharedAmount = integer("sharedAmount")
}