package com.application.sanroquestock.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {
    @Query("SELECT * FROM usersLoged")
    fun getAll(): LiveData<List<EntityUser>>

    @Query("SELECT * FROM usersLoged WHERE id IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<EntityUser>

    @Query("SELECT * FROM usersLoged WHERE username LIKE :user LIMIT 1")
    fun findByName(user: String): LiveData<EntityUser?>

    @Insert
    fun insertAll(users: List<EntityUser>)

    @Insert
    fun insertUser(user: EntityUser)

    @Delete
    fun delete(user: EntityUser)
}