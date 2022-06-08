package com.example.noteapp_k.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noteapp_k.NoteApplication
import com.example.noteapp_k.NoteViewModel
import com.example.noteapp_k.NotesAdapter
import com.example.noteapp_k.R
import com.example.noteapp_k.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: NoteViewModel by activityViewModels {
        NoteViewModel.NoteViewModelFactory((activity?.application as NoteApplication).database.noteDao())
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
viewModel.allNotes.observe(viewLifecycleOwner){
    binding.notesRecycler.layoutManager = LinearLayoutManager(requireContext())
    binding.notesRecycler.adapter = NotesAdapter(it)
}

        binding.newNotesBtn.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(R.id.action_homeFragment_to_createNotesFragment)

        }


    }
}







