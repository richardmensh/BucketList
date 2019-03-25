package com.example.bucketlist

import android.arch.persistence.room.*
import android.content.Context

@Database(entities = [Item::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract fun itemDAO(): ItemDAO

    companion object {
        var INSTANCE: AppDatabase? = null

        fun getAppDataBase(context: Context): AppDatabase? {
            if (INSTANCE == null){
                synchronized(AppDatabase::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "myDB").build()
                }
            }
            return INSTANCE
        }

        fun destroyDataBase(){
            INSTANCE = null
        }
    }
}