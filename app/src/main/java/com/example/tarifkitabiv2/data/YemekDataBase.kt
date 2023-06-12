package com.example.tarifkitabiv2.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Yemek::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class YemekDataBase:RoomDatabase() {
    abstract fun yemekDao():YemekDao

companion object{
    @Volatile private var instance:YemekDataBase?=null


    fun getDatabase(context:Context):YemekDataBase{
        val tempInstance= instance
        if (tempInstance!=null){
            return  tempInstance
        }
        synchronized(this){
            val instancex=Room.databaseBuilder(context.applicationContext,
            YemekDataBase::class.java,
            "yemek_database").build()
            instance=instancex
            return instancex
        }
    }
}
}