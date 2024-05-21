package com.example.planetplanet

import androidx.lifecycle.ViewModel

class PlantViewModel: ViewModel() {
    private val _plants = mutableListOf<Plant>()
    val plants: MutableList<Plant>
        get() = _plants

    private lateinit var _currentPlant:Plant
    val currentPlant:Plant
        get() = _currentPlant

    fun setCurrentPlant(plantEx:Plant){
        _currentPlant = plantEx
    }

//    fun findIndexForViewID(viewID:Int){
//        for()
//    }

    fun addPlant(newPlant:Plant){
        _plants.add(newPlant)
    }
}