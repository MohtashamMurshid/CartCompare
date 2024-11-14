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
import com.example.realestate.Item
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

        val appleDetails = Item(
            name = "Apples",
            imageResId = R.drawable.apples,
            unit = "/1kg",
            priceLow = "RM5.70",
            priceHigh = "RM6.30",
            jayaPrice = "RM5.70",
            vgPrice = "RM6.00",
            lotusPrice = "RM5.90",
            bigPrice = "RM6.20",
            aeonPrice = "RM6.30"
        )

        val mangoDetails = Item(
            name = "Mangoes",
            imageResId = R.drawable.mangoes,
            unit = "/1kg",
            priceLow = "RM6.00",
            priceHigh = "RM7.50",
            jayaPrice = "RM6.00",
            vgPrice = "RM7.50",
            lotusPrice = "RM6.20",
            bigPrice = "RM6.80",
            aeonPrice = "RM7.20"
        )

        val watermelonDetails = Item(
            name = "Watermelon",
            imageResId = R.drawable.watermelon,
            unit = "/1kg",
            priceLow = "RM2.20",
            priceHigh = "RM3.20",
            jayaPrice = "RM2.50",
            vgPrice = "RM3.00",
            lotusPrice = "RM2.20",
            bigPrice = "RM2.80",
            aeonPrice = "RM3.20"
        )

        val strawberryDetails = Item(
            name = "Strawberry",
            imageResId = R.drawable.strawberry,
            unit = "/250g",
            priceLow = "RM9.50",
            priceHigh = "RM11.50",
            jayaPrice = "RM9.50",
            vgPrice = "RM10.50",
            lotusPrice = "RM9.80",
            bigPrice = "RM10.90",
            aeonPrice = "RM11.50"
        )

        val bananaDetails = Item(
            name = "Bananas",
            imageResId = R.drawable.bananas,
            unit = "/1kg",
            priceLow = "RM3.20",
            priceHigh = "RM4.20",
            jayaPrice = "RM3.20",
            vgPrice = "RM4.00",
            lotusPrice = "RM3.50",
            bigPrice = "RM3.80",
            aeonPrice = "RM4.20"
        )
        // Retrieve the item passed from the activity and show the details
        arguments?.getString("item")?.let {
            val whatDetails = when(it) {
                "Apples" -> appleDetails
                "Mangoes" -> mangoDetails
                "Watermelon" -> watermelonDetails
                "Strawberry" -> strawberryDetails
                "Bananas" -> bananaDetails
                else -> null
            }

            whatDetails?.let{
                binding.itemName.text = it.name
                binding.itemImage.setImageResource(it.imageResId)
                binding.unit.text = it.unit
                binding.priceLow.text = it.priceLow
                binding.priceHigh.text = it.priceHigh
                binding.jayaPrice.text = it.jayaPrice
                binding.vgPrice.text = it.vgPrice
                binding.lotusPrice.text = it.lotusPrice
                binding.bigPrice.text = it.bigPrice
                binding.aeonPrice.text = it.aeonPrice

                Toast.makeText(requireContext(), "${it.name} Details Are Showing", Toast.LENGTH_SHORT).show()
            }
        }


        binding.backIcon.setOnClickListener{
            requireActivity().supportFragmentManager.popBackStack()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

}