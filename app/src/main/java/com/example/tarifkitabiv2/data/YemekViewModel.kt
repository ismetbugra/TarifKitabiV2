package com.example.tarifkitabiv2.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class YemekViewModel(application: Application):AndroidViewModel(application) {
    val readAllData:LiveData<List<Yemek>>
    private val repository:YemekRepository
    init {
        val yemekDao=YemekDataBase.getDatabase(getApplication()).yemekDao()
        repository= YemekRepository(yemekDao)
        readAllData=repository.readAllData
    }

    fun addYemek(yemek: Yemek){
        viewModelScope.launch(Dispatchers.IO){
            repository.addYemek(yemek)
        }
    }

    fun updateYemek(yemek: Yemek){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateYemek(yemek)
        }
    }
    fun deleteYemek(yemek: Yemek){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteYemek(yemek)
        }
    }
    fun deleteAllYemek(){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteAllYemek()
        }
    }



}