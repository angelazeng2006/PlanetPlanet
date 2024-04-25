package com.example.planetplanet

import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.planetplanet.databinding.ListItemLayoutBinding

class PlantViewHolder (val binding: ListItemLayoutBinding): RecyclerView.ViewHolder(binding.root){
    private lateinit var currentPlant: Plant
    init {
        binding.root.setOnClickListener{
                view -> Toast.makeText(
            view.context,currentPlant.plantName + " clicked!",
            Toast.LENGTH_SHORT
        ).show()
        }
    }
    fun bindCourse (plant: Plant){
        currentPlant = plant
        binding.textViewCourseTitle.text = currentPlant.plantName
        binding.textViewCourseInstructor.text = currentPlant.plantType
        binding.imageViewCourseImage.setImageResource(currentPlant.plantImageResourceId)
    }
}