package com.example.realestate.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.realestate.FragmentSwitch
import com.example.realestate.databinding.FragmentAddBinding

class AddFragment : Fragment() {
    //creating instanc of FruitFragment
    private val fruitsFragment = FruitsFragment()
    private val itemDetailsFragment = ItemDetailsFragment()

    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!

    //prepping UI: inflate xml elements, bind code with them & return the root view of addfragment.xml
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    //now that the binding is done, here you can setup UI interactions
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState) //mandatory

        // Attaching a listener to a TextView
        binding.fruitsTv.setOnClickListener {
            showCategoryFragment("Fruits")
        }

        //replaces itemsSectionFl with fruits fragment by default.
        if (savedInstanceState == null) {
            childFragmentManager.beginTransaction()
                .replace(binding.itemsSectionFl.id, fruitsFragment)
                .commit()
        }
    }

    //for cleaning up any references or resources that might cause memory leaks
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Prevent memory leaks
    }

    private fun showCategoryFragment(category: String){
        val transaction = requireActivity().supportFragmentManager.beginTransaction()

        when (category) {
            "Fruits" -> {
                transaction.replace(binding.itemsSectionFl.id, fruitsFragment)
                Toast.makeText(requireContext(), "Fruits Category", Toast.LENGTH_SHORT).show()

            }
            //dummy code for now:
            "Veggies" -> { }
            "Meat" -> { }
            "Fish" -> { }
        }
        transaction.commit() // .replace() must be followed by .commit()
    }


}