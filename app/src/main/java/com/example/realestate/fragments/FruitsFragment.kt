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

        //Click "+" Button
        val addFruits = mapOf(
            binding.addApple to "Apples",
            binding.addMango to "Mangoes",
            binding.addWatermelon to "Watermelon",
            binding.addStrawberry to "Strawberry",
            binding.addBanana to "Bananas"
        )
        addFruits.forEach { (button, fruitName) ->
            button.setOnClickListener{
                Toast.makeText(requireContext(), "${fruitName} added to cart", Toast.LENGTH_SHORT).show()
            }
        }

        //Click Card
        val fruitCards = mapOf(
            binding.appleCard to "Apple",
            binding.mangoCard to "Mangoes",
            binding.watermelonCard to "Watermelon",
            binding.strawberryCard to "Strawberry",
            binding.bananaCard to "Bananas"
        )
        fruitCards.forEach { (card, fruitName) ->
            card.setOnClickListener {
                (activity as? FragmentSwitch)?.showItemDetailFragment(fruitName)
            }
        }
    }



}