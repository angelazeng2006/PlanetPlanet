package com.example.planetplanet

import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.planetplanet.databinding.FragmentHomeBinding
import com.example.planetplanet.databinding.FragmentInfoBinding

class InfoFragment : Fragment() {
    private var _binding: FragmentInfoBinding? = null
    private val binding get() = _binding!!
    private val viewModel: PlantViewModel by activityViewModels()
    lateinit var mediaPlayer: MediaPlayer



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentInfoBinding.inflate(inflater, container, false)
        val rootView = binding.root
//        val args = InfoFragmentArgs.fromBundle(requireArguments())
        val plantRightNow:Plant = viewModel.currentPlant
        binding.nameText.text = plantRightNow.plantName
        binding.waterReminderText.text = "Water in ${plantRightNow.lastTimeWatered} days."
        binding.notesText.text = "Notes: ${plantRightNow.notes}"

        var plantTypeOfClick:Int
        if (plantRightNow.plantType.equals("Aloe Vera"))
            binding.plantImage.setImageResource(R.drawable.aloe_vera)
        else if (plantRightNow.plantType.equals("Cactus"))
            binding.plantImage.setImageResource(R.drawable.cactus2)
        else if (plantRightNow.plantType.equals("Monstera"))
            binding.plantImage.setImageResource(R.drawable.monstera)
        else if (plantRightNow.plantType.equals("Peace Lily"))
            binding.plantImage.setImageResource(R.drawable.peace_lily)
        else if (plantRightNow.plantType.equals("Snake Plant"))
            binding.plantImage.setImageResource(R.drawable.snake_plant)
        else
            binding.plantImage.setImageResource(R.drawable.pothos2)
        binding.returnButton.setOnClickListener {
            mediaPlayer = MediaPlayer.create(context, R.raw.buttonpress)
            mediaPlayer.start()

            val action = InfoFragmentDirections.actionInfoFragmentToHomeFragment("",0,"","")
            rootView.findNavController().navigate(action)
        }
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object: MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.options_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return NavigationUI.onNavDestinationSelected(menuItem, requireView().findNavController())
            }

        },viewLifecycleOwner, Lifecycle.State.RESUMED)
        return rootView
    }
}