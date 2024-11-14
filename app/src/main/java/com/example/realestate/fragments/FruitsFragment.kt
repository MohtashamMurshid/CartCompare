package com.example.realestate.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.realestate.FragmentSwitch
import com.example.realestate.databinding.FragmentAddBinding.*
import com.example.realestate.databinding.FragmentFruitsBinding

class FruitsFragment : Fragment() {

    private var fragmentSwitch: FragmentSwitch? = null
    private var _binding: FragmentFruitsBinding? = null //we must name xml files like: fragment_A, and Android studio will automatically generate class called AFragment.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFruitsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //DIRECTLY CLICKING ADD
        binding.addApple.setOnClickListener{
            //call function to increase item number in cart
            Toast.makeText(requireContext(), "1kg of Apple added to cart", Toast.LENGTH_SHORT).show()
        }
        binding.addMangoes.setOnClickListener{
            //call function to increase item number in cart
            Toast.makeText(requireContext(), "1kg of Mangoes added to cart", Toast.LENGTH_SHORT).show()
        }
        binding.addWatermelon.setOnClickListener{
            //call function to increase item number in cart
            Toast.makeText(requireContext(), "Watermelon added to cart", Toast.LENGTH_SHORT).show()
        }
        binding.addStrawberry.setOnClickListener{
            //call function to increase item number in cart
            Toast.makeText(requireContext(), "Strawberries added to cart", Toast.LENGTH_SHORT).show()
        }
        binding.addBananas.setOnClickListener{
            //call function to increase item number in cart
            Toast.makeText(requireContext(), "Bananas added to cart", Toast.LENGTH_SHORT).show()
        }

        //CLICKING THE CARD
        binding.appleCard.setOnClickListener{
            (activity as? FragmentSwitch)?.showItemDetailFragment("Apple") //transaction & calling of showItemDetails("Apple") occurs
//            val activity = activity
//            if (activity is FragmentSwitch) {
//                Toast.makeText(requireContext(), "navigating to apple details", Toast.LENGTH_SHORT).show()
//                activity.showItemDetailFragment("Apple")
//            } else {
//                Toast.makeText(requireContext(), "Activity does not implement FragmentSwitch", Toast.LENGTH_SHORT).show()
//            }
        }
    }

//

//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        try {
//            fragmentSwitch = context as FragmentSwitch
//        } catch (castException: ClassCastException) {
//            throw RuntimeException("$context must implement FragmentSwitch")
//        }
//    }
//
//    override fun onDetach() {
//        super.onDetach()
//        fragmentSwitch = null
//    }

}