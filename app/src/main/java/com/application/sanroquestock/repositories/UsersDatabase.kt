package com.application.sanroquestock.repositories

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.application.sanroquestock.model.UserDao
import com.application.sanroquestock.model.EntityUser

@Database(entities = [EntityUser::class], version = 1)
abstract class UsersDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    companion object {
        var INSTANCE: UsersDatabase? = null

        fun getAppDataBase(context: Context): UsersDatabase? {
            if (INSTANCE == null){
                synchronized(UsersDatabase::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext, UsersDatabase::class.java, "userDatabase")
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