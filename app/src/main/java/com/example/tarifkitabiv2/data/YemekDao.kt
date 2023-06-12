package com.example.tarifkitabiv2.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface YemekDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addYemek(yemek:Yemek)

    @Update
    suspend fun updateYemek(yemek:Yemek)

    @Delete
    suspend fun  deleteYemek(yemek:Yemek)

    @Query("DELETE FROM yemek_table")
    suspend fun deleteAllYemek()

    @Query("SELECT*FROM yemek_table ORDER BY id ASC")
    fun readAllData():LiveData<List<Yemek>>




}