package com.application.sanroquestock.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "storeItems")
data class EntityItems(
        @PrimaryKey(autoGenerate = true) var id: Int = 0,
        @ColumnInfo(name = "barcode") var barcode: String?,
        @ColumnInfo(name = "description") var description: String?,
        @ColumnInfo(name = "price") var price: Int?,
        @ColumnInfo(name = "cantidad") var cantidad: Int?
)