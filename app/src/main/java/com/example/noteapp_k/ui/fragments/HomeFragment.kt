package com.example.noteapp_k.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
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
    //private val adapter: NotesAdapter by lazy { NotesAdapter() }


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
            adapter = NotesAdapter(it)
            // adapter.setData(it)
            binding.notesRecycler.adapter = adapter
            oldMyNotes = it as ArrayList<Note>

        }
        binding.filterAll.setOnClickListener {
            viewModel.allNotes.observe(viewLifecycleOwner) {
                binding.notesRecycler.layoutManager = GridLayoutManager(requireContext(), 2)
                adapter = NotesAdapter(it)
                // adapter.setData(it)
                binding.notesRecycler.adapter = adapter
                oldMyNotes = it as ArrayList<Note>

            }
            binding.filterhigh.setOnClickListener {
                viewModel.getHighNotes.observe(viewLifecycleOwner) {
                    binding.notesRecycler.layoutManager = GridLayoutManager(requireContext(), 2)

                    // adapter.setData(it)
                    adapter = NotesAdapter(it)
                    binding.notesRecycler.adapter = adapter
                    oldMyNotes = it as ArrayList<Note>
                }
                binding.filtermedium.setOnClickListener {
                    viewModel.getMediumNotes.observe(viewLifecycleOwner) {
                        binding.notesRecycler.layoutManager = GridLayoutManager(requireContext(), 2)

                        //adapter.setData(it)
                        adapter = NotesAdapter(it)
                        binding.notesRecycler.adapter = adapter

                        oldMyNotes = it as ArrayList<Note>
                    }
                }
                binding.filterlow.setOnClickListener {
                    viewModel.getLowNotes.observe(viewLifecycleOwner) {
                        binding.notesRecycler.layoutManager = GridLayoutManager(requireContext(), 2)

                        //adapter.setData(it)
                        adapter = NotesAdapter(it)
                        binding.notesRecycler.adapter = adapter
                        oldMyNotes = it as ArrayList<Note>

                    }
                }


            }

        }


    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)
        val item = menu.findItem(R.id.app_bar_search)
        val searchView = item.actionView as SearchView
        searchView.queryHint = "enter notes"
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                notesFilteting(p0)
                return true
            }

        })
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun notesFilteting(p0: String?) {
        Log.e("@@@", "notesFilteting: $p0")
        val newFilteredList = arrayListOf<Note>()
        for (i in oldMyNotes){
            if(i.title.contains(p0!!)||i.subTitle.contains(p0!!)){
                newFilteredList.add(i)
            }
        }
        adapter.filtering(newFilteredList)

    }


/*





    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.item_home_action_bar, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            R.id.add_item -> {
                findNavController().navigate(R.id.action_homeFragment_to_createNotesFragment)
                Toast.makeText(requireContext(),"merhaba",Toast.LENGTH_LONG).show()
            }
            R.id.app_bar_search->{
                Toast.makeText(requireContext(),"merhaba",Toast.LENGTH_LONG).show()

            }

            // NavigateUp

            else -> {
                return super.onOptionsItemSelected(item)
            }


        }

        return true
    }






 */

}








