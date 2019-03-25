package com.example.bucketlist

import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.*

@Dao
interface ItemDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertItem(item: Item)

    @Update
    fun updateItem(item: Item)

    @Delete
    fun deleteItem(item: Item)

    @Delete
    fun deleteItem(item: List<Item>)

    @Query("SELECT * FROM Item")
    fun getItems(): List<Item>
}