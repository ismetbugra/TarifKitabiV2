package com.example.tarifkitabiv2.data

import androidx.lifecycle.LiveData

class YemekRepository(private val yemekDao: YemekDao) {
    val readAllData:LiveData<List<Yemek>> = yemekDao.readAllData()
    suspend fun addYemek(yemek:Yemek){
        yemekDao.addYemek(yemek)
    }
    suspend fun deleteYemek(yemek: Yemek){
        yemekDao.deleteYemek(yemek)
    }
    suspend fun updateYemek(yemek: Yemek){
        yemekDao.updateYemek(yemek)
    }
    suspend fun deleteAllYemek(){
        yemekDao.deleteAllYemek()
    }
}