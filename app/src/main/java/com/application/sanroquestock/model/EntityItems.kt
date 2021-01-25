package com.application.sanroquestock.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "storeItems")
data class EntityItems(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "barcode") val barcode: String?,
    @ColumnInfo(name = "description") val description: String?,
    @ColumnInfo(name = "price") val price: Int?
)