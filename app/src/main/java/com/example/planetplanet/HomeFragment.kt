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
import androidx.activity.viewModels
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.planetplanet.databinding.FragmentHomeBinding
import com.example.planetplanet.databinding.FragmentStartBinding
import androidx.fragment.app.viewModels
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.ui.NavigationUI


class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: PlantViewModel by activityViewModels()
    lateinit var mediaPlayer: MediaPlayer

//    val plants = mutableListOf<Plant>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val rootView = binding.root
        val args = HomeFragmentArgs.fromBundle(requireArguments())

        if(args.name!="" && args.plantType!="") {

            var plantTypeOfClick:Int
            if (args.plantType.equals("Aloe Vera"))
                plantTypeOfClick = R.drawable.aloe_vera
            else if (args.plantType.equals("Cactus"))
                plantTypeOfClick = R.drawable.cactus2
            else if (args.plantType.equals("Monstera"))
                plantTypeOfClick = R.drawable.monstera
            else if (args.plantType.equals("Peace Lily"))
                plantTypeOfClick = R.drawable.peace_lily
            else if (args.plantType.equals("Snake Plant"))
                plantTypeOfClick = R.drawable.snake_plant
            else
                plantTypeOfClick = R.drawable.pothos2


            viewModel.plants.add(Plant(args.name, args.plantType, args.lastTimeWatered, plantTypeOfClick, args.notes))
        }

        val mAdapter = PlantAdapter(viewModel.plants, viewModel)
        binding.recyclerView.adapter = mAdapter

        binding.addButton.setOnClickListener {
            mediaPlayer = MediaPlayer.create(context, R.raw.buttonpress)
            mediaPlayer.start()

            val action = HomeFragmentDirections.actionHomeFragmentToAddFragment()
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