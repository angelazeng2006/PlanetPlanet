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
import androidx.lifecycle.Lifecycle
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.planetplanet.databinding.FragmentAddBinding
import com.example.planetplanet.databinding.FragmentStartBinding

class AddFragment : Fragment() {
    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!
    lateinit var mediaPlayer: MediaPlayer

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddBinding.inflate(inflater, container, false)
        val rootView = binding.root
        binding.aloeVeraButton.setOnClickListener {
            mediaPlayer = MediaPlayer.create(context, R.raw.buttonpress)
            mediaPlayer.start()
            val plantType = "Aloe Vera"

            val action = AddFragmentDirections.actionAddFragmentToInputFragment(plantType)
            rootView.findNavController().navigate(action)
        }
        binding.cactusButton.setOnClickListener {
            mediaPlayer = MediaPlayer.create(context, R.raw.buttonpress)
            mediaPlayer.start()
            val plantType = "Cactus"


            val action = AddFragmentDirections.actionAddFragmentToInputFragment(plantType)
            rootView.findNavController().navigate(action)
        }

        binding.peaceLilyButton.setOnClickListener {
            mediaPlayer = MediaPlayer.create(context, R.raw.buttonpress)
            mediaPlayer.start()
            val plantType = "Peace Lily"


            val action = AddFragmentDirections.actionAddFragmentToInputFragment(plantType)
            rootView.findNavController().navigate(action)
        }

        binding.pothosButton.setOnClickListener {
            mediaPlayer = MediaPlayer.create(context, R.raw.buttonpress)
            mediaPlayer.start()
            val plantType = "Pothos"


            val action = AddFragmentDirections.actionAddFragmentToInputFragment(plantType)
            rootView.findNavController().navigate(action)
        }

        binding.snakeButton.setOnClickListener {
            mediaPlayer = MediaPlayer.create(context, R.raw.buttonpress)
            mediaPlayer.start()
            val plantType = "Snake Plant"


            val action = AddFragmentDirections.actionAddFragmentToInputFragment(plantType)
            rootView.findNavController().navigate(action)
        }

        binding.monsteraButton.setOnClickListener {
            mediaPlayer = MediaPlayer.create(context, R.raw.buttonpress)
            mediaPlayer.start()
            val plantType = "Monstera"


            val action = AddFragmentDirections.actionAddFragmentToInputFragment(plantType)
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