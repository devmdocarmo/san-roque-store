package com.application.sanroquestock.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "usersLoged")
data class EntityUser(
        @PrimaryKey(autoGenerate = true) val id: Int,
        @ColumnInfo(name = "username") val username: String?,
        @ColumnInfo(name = "passEncript") val passEncript: String?
)