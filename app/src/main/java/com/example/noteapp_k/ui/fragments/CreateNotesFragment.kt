package com.example.noteapp_k.ui.fragments

import android.os.Bundle
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.noteapp_k.NoteApplication
import com.example.noteapp_k.NoteViewModel
import com.example.noteapp_k.R
import com.example.noteapp_k.databinding.FragmentCreateNotesBinding
import com.example.noteapp_k.model.Note
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import java.util.*

class CreateNotesFragment : BottomSheetDialogFragment() {


    //lateinit var binding: FragmentCreateNotesBinding
    private val binding by viewBinding(FragmentCreateNotesBinding::bind)
    private val viewModel: NoteViewModel by activityViewModels {
        NoteViewModel.NoteViewModelFactory((activity?.application as NoteApplication).database.noteDao())
    }
    var priority: String = "1"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

    return inflater.inflate(R.layout.fragment_create_notes,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.greenPriority.setOnClickListener {
            priority = "1"
            binding.greenPriority.setImageResource(R.drawable.ic_baseline_done_24)
            binding.yellowPriority.setImageResource(0)
            binding.redPriority.setImageResource(0)
        }
        binding.yellowPriority.setOnClickListener {
            priority = "2"
            binding.greenPriority.setImageResource(0)
            binding.yellowPriority.setImageResource(R.drawable.ic_baseline_done_24)
            binding.redPriority.setImageResource(0)

        }
        binding.redPriority.setOnClickListener {
            priority = "3"
            binding.greenPriority.setImageResource(0)
            binding.yellowPriority.setImageResource(0)
            binding.redPriority.setImageResource(R.drawable.ic_baseline_done_24)

        }

        binding.doneNotesBtn.setOnClickListener {
            createNotes(it)
        }
    }

    private fun createNotes(it: View?) {
        val title = binding.notesTitle.text.toString()
        val subTitle = binding.notesSubtitle.text.toString()
        val notes = binding.notesData.text.toString()
        val d = Date()
        val date: CharSequence = DateFormat.format("d MMMM yyyy ", d.time)
        println(date)
        val data = Note(
            title = title,
            subTitle = subTitle,
            notes = notes,
            date = date.toString(), priority =priority)
        viewModel.insertNote(data)
        findNavController().navigate(R.id.action_createNotesFragment_to_homeFragment)


        Toast.makeText(requireContext(), "succes", Toast.LENGTH_LONG).show()

    }


}