package com.example.planetplanet

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.planetplanet.databinding.ListItemLayoutBinding

class PlantAdapter(val plantList:List<Plant>,val viewModel: PlantViewModel): RecyclerView.Adapter<PlantViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantViewHolder {
        val binding = ListItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PlantViewHolder(binding, viewModel)
    }

    override fun getItemCount(): Int {
        return plantList.size
    }

    override fun onBindViewHolder(currentViewHolder: PlantViewHolder, position: Int) {
        val currentPlant = plantList[position]
        currentViewHolder.bindCourse(currentPlant)
    }

}