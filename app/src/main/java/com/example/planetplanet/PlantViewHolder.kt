package com.example.planetplanet

import android.media.MediaPlayer
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.planetplanet.databinding.ListItemLayoutBinding
import androidx.navigation.findNavController
import androidx.fragment.app.activityViewModels



class PlantViewHolder (val binding: ListItemLayoutBinding, val viewModel: PlantViewModel): RecyclerView.ViewHolder(binding.root){
    lateinit var mediaPlayer: MediaPlayer
    private lateinit var currentPlant: Plant

    init {
        binding.root.setOnClickListener{ view ->
            mediaPlayer = MediaPlayer.create(binding.root.context, R.raw.buttonpress)
            mediaPlayer.start()
//            call view model method to set plant ID
            viewModel.setCurrentPlant(currentPlant)
            binding.root.findNavController().navigate(R.id.action_homeFragment_to_infoFragment)
        }

    }
    fun bindCourse (plant: Plant){
        currentPlant = plant
        binding.textViewPlantTitle.text = currentPlant.plantName
        binding.textViewPlantType.text = currentPlant.plantType
        binding.imageViewPlantImage.setImageResource(currentPlant.plantImageResourceId)
    }
}