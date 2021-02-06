package com.application.sanroquestock.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


@Dao
interface ItemsDao {
    @Query("SELECT * FROM storeItems")
    fun getAll(): LiveData<List<EntityItems?>>

    @Query("SELECT * FROM storeItems WHERE barcode IN (:itemBarcode)")
    fun loadAllByIds(itemBarcode: Int): List<EntityItems?>

    @Insert
    fun insertAll(items: List<EntityItems>)

    @Insert
    fun insertUser(item: EntityItems)

    @Delete
    fun delete(item: EntityItems)
}