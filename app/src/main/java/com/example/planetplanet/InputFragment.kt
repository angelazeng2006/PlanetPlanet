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
import android.widget.SeekBar
import android.widget.Toast
import androidx.core.graphics.drawable.toDrawable
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.planetplanet.databinding.FragmentInputBinding

class InputFragment : Fragment() {
    private var _binding: FragmentInputBinding? = null
    private val binding get() = _binding!!
    lateinit var mediaPlayer: MediaPlayer

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInputBinding.inflate(inflater, container, false)
        val rootView = binding.root
        setUpTipSeekBar()
        val myDrawable = getResources().getDrawable(R.drawable.plantthumb)
        binding.waterInput.setThumb(myDrawable);

        binding.nextButton.setOnClickListener {
            mediaPlayer = MediaPlayer.create(context, R.raw.buttonpress)
            mediaPlayer.start()

            val name = binding.nameInput.text.toString()
            val args = InputFragmentArgs.fromBundle(requireArguments())

            val plantType = args.plantType
            val lastTimeWatered = binding.waterInput.progress
            val notes = binding.notesInput.text.toString()

            if(name.equals("")){
                Toast.makeText(context,"You must enter a name for your plant!", Toast.LENGTH_SHORT).show()
            }
            else {
                val action = InputFragmentDirections.actionInputFragmentToHomeFragment(
                    name,
                    lastTimeWatered,
                    notes,
                    plantType
                )
                rootView.findNavController().navigate(action)
            }
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

    fun setUpTipSeekBar(){
        binding.waterInput.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(tipBar: SeekBar) {
                binding.enterWaterTimeText.text = "Water every ${binding.waterInput.progress} days:"
            }
        })
    }
}