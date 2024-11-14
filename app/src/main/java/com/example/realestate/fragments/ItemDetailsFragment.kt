package com.example.realestate.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.children
import androidx.fragment.app.Fragment
import com.example.realestate.R
import com.example.realestate.databinding.FragmentAddBinding
import com.example.realestate.databinding.FragmentAddBinding.*
import com.example.realestate.databinding.FragmentFruitsBinding
import com.example.realestate.databinding.FragmentItemDetailsBinding
import com.example.realestate.Item

private typealias priceCardList = Pair<Float, View>

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

        //setup the details for each item
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

//                Toast.makeText(requireContext(), "${it.name} Details Are Showing", Toast.LENGTH_SHORT).show()
            }
        }

        //navigates back
        binding.backIcon.setOnClickListener{
            requireActivity().supportFragmentManager.popBackStack()
        }

        //setting up list pair of card & price, for sorting cards based on price order
        val priceCardList = listOf(
            Pair(binding.aeonPrice.text.substring(2).toFloat(), binding.aeonCard),
            Pair(binding.jayaPrice.text.substring(2).toFloat(), binding.jayaCard),
            Pair(binding.bigPrice.text.substring(2).toFloat(), binding.bigCard),
            Pair(binding.lotusPrice.text.substring(2).toFloat(), binding.lotusCard),
            Pair(binding.vgPrice.text.substring(2).toFloat(), binding.vgCard)
        )
        sortIncrease(priceCardList)

        //switch sortInc to sortDec
        binding.sort.setOnClickListener{
            if (binding.sort.tag == "increase"){ //if sort is currently inc, change to dec
                binding.sort.setImageResource(R.drawable.sort_decrease)
                binding.sort.tag = "decrease"
                sortDecrease(priceCardList)
            }
            else{
                binding.sort.setImageResource(R.drawable.sort_increase)
                binding.sort.tag = "increase"
                sortIncrease(priceCardList)
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    private fun sortIncrease(list: List<Pair<Float, View>>) {
        //sort low to high price
        val sortedPriceCardList= list.sortedBy { it.first }

        //remove all cards to rearrange later
        binding.cardLayout.removeAllViews()

        //set color of price tags, low:green, mid: orange, high: red
        sortedPriceCardList.forEachIndexed{ index, pair ->
            when(index){
                0 -> setPriceColor(pair.second.id, "#34DC1E") //first index: green
                sortedPriceCardList.size-1 -> setPriceColor(pair.second.id, "#FF0004") //last index: red
                else -> setPriceColor(pair.second.id, "#FF9900") //mid: orange
            }
        }
        //add cards with the sorted order
        sortedPriceCardList.forEach { pair ->
            binding.cardLayout.addView(pair.second)
        }
    }

    private fun sortDecrease(list: List<Pair<Float, View>>) {
        val sortedPriceCardList = list.sortedByDescending{ it.first }
        binding.cardLayout.removeAllViews()
        sortedPriceCardList.forEach { pair ->
            binding.cardLayout.addView(pair.second)
        }
    }

    private fun setPriceColor(id: Int, color: String){
        when(id){
            R.id.aeonCard -> binding.aeonPrice.setTextColor(Color.parseColor(color))
            R.id.jayaCard -> binding.jayaPrice.setTextColor(Color.parseColor(color))
            R.id.bigCard -> binding.bigPrice.setTextColor(Color.parseColor(color))
            R.id.lotusCard -> binding.lotusPrice.setTextColor(Color.parseColor(color))
            R.id.vgCard -> binding.vgPrice.setTextColor(Color.parseColor(color))
        }
    }


}