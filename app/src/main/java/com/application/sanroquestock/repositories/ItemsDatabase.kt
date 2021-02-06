package com.application.sanroquestock.repositories

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.application.sanroquestock.model.EntityItems
import com.application.sanroquestock.model.ItemsDao

@Database(entities = [EntityItems::class], version = 1)
abstract class ItemsDatabase : RoomDatabase() {
    abstract fun itemsDao(): ItemsDao
    companion object {
        var INSTANCE: ItemsDatabase? = null

        fun getAppDataBase(context: Context, user: String): ItemsDatabase? {
            if (INSTANCE == null){
                synchronized(ItemsDatabase::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext, ItemsDatabase::class.java, "userDatabase$user")
                            .enableMultiInstanceInvalidation()
                            .build()
                }
            }
            return INSTANCE
        }

        fun destroyDataBase(){
            INSTANCE = null
        }
    }
}