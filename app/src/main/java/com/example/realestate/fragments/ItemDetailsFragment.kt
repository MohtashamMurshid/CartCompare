package com.example.realestate.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.realestate.Item
import com.example.realestate.R
import com.example.realestate.databinding.FragmentItemDetailsBinding
import com.example.realestate.ItemRepository
import com.example.realestate.SharedCartViewModel

private typealias priceCardList = Pair<Float, View>

class ItemDetailsFragment : Fragment() {

    private val sharedViewModel: SharedCartViewModel by activityViewModels()
    private var _binding: FragmentItemDetailsBinding? = null //we must name xml files like: fragment_A, and Android studio will automatically generate class called AFragment.
    private val binding get() = _binding!!
    private var item: String? = null
    private var currentQuantity: Int = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentItemDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Retrieve the item passed from the activity and show the details. bcs the detail has to depend on which item user selected
        arguments?.let { bundle ->
            val itemName = bundle.getString("item") // Corrected usage of getString
            bundle.getInt("imageResId", R.drawable.placeholder_image) // Corrected usage of getInt

            val itemDetails = itemName?.let { ItemRepository.getItemByName(it) }

            //filling up the xml template with the item details
            itemDetails?.let { details ->
                binding.itemName.text = details.name
                binding.itemImage.setImageResource(details.imageResId)
                binding.unit.text = details.unit
                binding.priceLow.text = details.priceLow
                binding.priceHigh.text = details.priceHigh
                binding.jayaPrice.text = details.jayaPrice
                binding.vgPrice.text = details.vgPrice
                binding.lotusPrice.text = details.lotusPrice
                binding.bigPrice.text = details.bigPrice
                binding.aeonPrice.text = details.aeonPrice
            }
        }
        // Update quantity
        updateQuantityDisplay()

        // Increment quantity
        binding.addItem.setOnClickListener {
            currentQuantity++
            updateQuantityDisplay()
        }

        // Decrement quantity
        binding.minusItem.setOnClickListener {
            if (currentQuantity > 0) {
                currentQuantity--
                updateQuantityDisplay()
            } else {
                Toast.makeText(requireContext(), "Quantity can't be less than 0", Toast.LENGTH_SHORT).show()
            }
        }
        binding.cartItemImg.setOnClickListener {
            if (currentQuantity > 0) {
                val item = Item(
                    name = binding.itemName.text.toString(),
                    category = "Fruits",
                    unit = binding.unit.text.toString(),
                    priceLow = binding.priceLow.text.toString(),
                    price = binding.priceLow.text.toString(),
                    imageResId = arguments?.getInt("imageResId") ?: R.drawable.placeholder_image, // Pass correct imageResId
                    priceHigh = binding.priceHigh.text.toString(),
                    aeonPrice = binding.aeonPrice.text.toString(),
                    bigPrice = binding.bigPrice.text.toString(),
                    lotusPrice = binding.lotusPrice.text.toString(),
                    jayaPrice = binding.jayaPrice.text.toString(),
                    vgPrice = binding.vgPrice.text.toString(),
                    quantity = currentQuantity
                )
                sharedViewModel.addItem(item)
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
        _binding = null // Prevent memory leaks
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
        //sort high to low price
        val sortedPriceCardList = list.sortedByDescending{ it.first }

        //remove all cards to rearrange later
        binding.cardLayout.removeAllViews()

        //no need to set color again because the app sorts by increase by default, and in sortIncrease we have set up the color tags
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
    // Update quantity in the UI
    private fun updateQuantityDisplay() {
        binding.quantityTv.text = currentQuantity.toString()
    }


}