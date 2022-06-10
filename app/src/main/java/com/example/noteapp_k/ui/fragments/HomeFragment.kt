package com.example.noteapp_k.ui.fragments

import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.view.*
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noteapp_k.NoteApplication
import com.example.noteapp_k.NoteViewModel
import com.example.noteapp_k.NotesAdapter
import com.example.noteapp_k.R
import com.example.noteapp_k.databinding.FragmentHomeBinding
import com.example.noteapp_k.model.Note


class HomeFragment : Fragment() {
    private val viewModel: NoteViewModel by activityViewModels {
        NoteViewModel.NoteViewModelFactory((activity?.application as NoteApplication).database.noteDao())
    }
    var oldMyNotes = arrayListOf<Note>()
    private lateinit var binding: FragmentHomeBinding
    lateinit var adapter: NotesAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        setHasOptionsMenu(true)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.allNotes.observe(viewLifecycleOwner) {
            binding.notesRecycler.layoutManager = GridLayoutManager(requireContext(), 2)
            adapter= NotesAdapter(it)
            binding.notesRecycler.adapter =adapter

        }
        binding.filterAll.setOnClickListener {
            viewModel.allNotes.observe(viewLifecycleOwner) {
                binding.notesRecycler.layoutManager = GridLayoutManager(requireContext(), 2)
               adapter= NotesAdapter(it)
                binding.notesRecycler.adapter =adapter

            }
            binding.filterhigh.setOnClickListener {
                viewModel.getHighNotes.observe(viewLifecycleOwner) {
                    binding.notesRecycler.layoutManager = GridLayoutManager(requireContext(), 2)
                    adapter= NotesAdapter(it)
                    binding.notesRecycler.adapter =adapter
                }
                binding.filtermedium.setOnClickListener {
                    viewModel.getMediumNotes.observe(viewLifecycleOwner) {
                        binding.notesRecycler.layoutManager = GridLayoutManager(requireContext(), 2)
                        adapter= NotesAdapter(it)
                        binding.notesRecycler.adapter =adapter
                    }
                }
                binding.filterlow.setOnClickListener {
                    viewModel.getLowNotes.observe(viewLifecycleOwner) {
                        binding.notesRecycler.layoutManager = GridLayoutManager(requireContext(), 2)
                        adapter= NotesAdapter(it)
                        binding.notesRecycler.adapter =adapter
                    }
                }


            }
        }
    }





    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.item_home_action_bar, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            R.id.add_item -> {
                findNavController().navigate(R.id.action_homeFragment_to_createNotesFragment)
            }
            R.id.search_app -> {


                Toast.makeText(context, "Search", Toast.LENGTH_SHORT).show()
            }

            // NavigateUp

            else -> {
                return super.onOptionsItemSelected(item)
            }


        }

        return true
    }




}








