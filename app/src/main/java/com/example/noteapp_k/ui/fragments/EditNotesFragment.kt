package com.example.noteapp_k.ui.fragments

import android.icu.text.CaseMap
import android.os.Bundle
import android.text.format.DateFormat
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.noteapp_k.NoteApplication
import com.example.noteapp_k.NoteViewModel
import com.example.noteapp_k.R
import com.example.noteapp_k.databinding.FragmentEditNotesBinding
import com.example.noteapp_k.model.Note
import java.util.*


class EditNotesFragment : Fragment() {
    val args: EditNotesFragmentArgs by navArgs()
    lateinit var binding: FragmentEditNotesBinding
    var priority = "1"



    private val viewModel: NoteViewModel by activityViewModels {
        NoteViewModel.NoteViewModelFactory((activity?.application as NoteApplication).database.noteDao())
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentEditNotesBinding.inflate(layoutInflater)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.edtnotesData.setText(args.old.notes)
        binding.edtnotesSubtitle.setText(args.old.subTitle)
        binding.edtnotesTitle.setText(args.old.title)
        when (args.old.priority) {
            "1" -> {
                priority = "1"
                binding.greenPriority.setImageResource(R.drawable.ic_baseline_done_24)
                binding.yellowPriority.setImageResource(0)
                binding.redPriority.setImageResource(0)

            }
            "2" -> {
                priority = "2"
                binding.greenPriority.setImageResource(0)
                binding.yellowPriority.setImageResource(R.drawable.ic_baseline_done_24)
                binding.redPriority.setImageResource(0)
            }
            "3" -> {
                priority = "3"
                binding.greenPriority.setImageResource(0)
                binding.yellowPriority.setImageResource(0)
                binding.redPriority.setImageResource(R.drawable.ic_baseline_done_24)
            }
        }
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
        binding.edtdoneNotesBtn.setOnClickListener {
            updateNotes(it)
        }
    }



    private fun updateNotes(it: View?) {

        val title = binding.edtnotesTitle.text.toString()
        val subTitle = binding.edtnotesSubtitle.text.toString()
        val notes = binding.edtnotesData.text.toString()
        val d = Date()
        val date: CharSequence = DateFormat.format("MMMM d, yyyy ", d.time)
        val data = Note(
            id=args.old.id,
            title = title,
            subTitle = subTitle,
            notes = notes,
            date = date.toString(), priority = priority)
        viewModel.update(data)
        Toast.makeText(requireContext(), "updated", Toast.LENGTH_LONG).show()
        Log.e("@@@",    "updateNotes$title :$subTitle,$notes")
        Navigation.findNavController(it!!).navigate(R.id.action_editNotesFragment_to_homeFragment)    }

}




