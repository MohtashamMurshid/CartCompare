package com.example.realestate.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.realestate.R
import com.example.realestate.databinding.FragmentAddBinding
import com.example.realestate.databinding.FragmentAddBinding.*
import com.example.realestate.databinding.FragmentFruitsBinding
import com.example.realestate.databinding.FragmentItemDetailsBinding

class ItemDetailsFragment : Fragment() {

    private var _binding: FragmentItemDetailsBinding? = null //we must name xml files like: fragment_A, and Android studio will automatically generate class called AFragment.
    private val binding get() = _binding!!
    private var item: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentItemDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Retrieve the item passed from the activity and show the details
        arguments?.getString("item")?.let {
            showItemDetails(it)
        }

        binding.backIcon.setOnClickListener{
            requireActivity().supportFragmentManager.popBackStack()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    fun showItemDetails(item: String){

        when(item){
            "Apple"->{
                //setup apple details
                Toast.makeText(requireContext(), "Apple Details Are Showing", Toast.LENGTH_SHORT).show()
            }
            "Mangoes"->{

            }
        }
    }

}