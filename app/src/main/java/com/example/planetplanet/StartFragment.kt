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
import com.example.planetplanet.databinding.FragmentStartBinding

class StartFragment : Fragment() {
    private var _binding: FragmentStartBinding? = null
    private val binding get() = _binding!!
    lateinit var mediaPlayer: MediaPlayer

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStartBinding.inflate(inflater, container, false)
        val rootView = binding.root
        binding.homeButton.setOnClickListener {
            mediaPlayer = MediaPlayer.create(context, R.raw.buttonpress)
            mediaPlayer.start()

            val action = StartFragmentDirections.actionStartFragmentToHomeFragment("",0,"","")
            rootView.findNavController().navigate(action)
        }
        binding.addButton.setOnClickListener {
            mediaPlayer = MediaPlayer.create(context, R.raw.buttonpress)
            mediaPlayer.start()

            val action = StartFragmentDirections.actionStartFragmentToAddFragment()
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