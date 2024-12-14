package com.example.realestate.fragments

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.realestate.ItemRepository
import com.example.realestate.R
import com.example.realestate.Item
import com.example.realestate.SharedCartViewModel
import com.example.realestate.databinding.FragmentAddBinding

class AddFragment : Fragment() {

    private val sharedViewModel: SharedCartViewModel by activityViewModels()
    private val itemDetailsFragment = ItemDetailsFragment()
    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val textViewList = listOf(
            binding.fruitsTv,
            binding.veggiesTv,
            binding.meatTv,
            binding.fishTv,
            binding.seafoodTv,
            binding.dairyTv,
            binding.snacksTv
        )

        textViewList.forEach { textView ->
            textView.setOnClickListener {
                // Reset text color for all categories
                textViewList.forEach { it.setTextColor(Color.parseColor("#34DC1E")) }
                // Highlight the selected category
                textView.setTextColor(Color.parseColor("#007AFF"))

                // Load items for the selected category
                binding.gridLayout.removeAllViews()
                val category = when (textView.id) {
                    binding.fruitsTv.id -> "Fruits"
                    binding.veggiesTv.id -> "Veggies"
                    binding.meatTv.id -> "Meat"
                    binding.fishTv.id -> "Fish"
                    binding.seafoodTv.id -> "Seafood"
                    binding.dairyTv.id -> "Eggs & Dairy"
                    binding.snacksTv.id -> "Snacks"
                    else -> ""
                }
                showItems(category)
            }
        }

        // Load the default category
        showItems("Fruits")
    }

    //for cleaning up any references or resources that might cause memory leaks
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Prevent memory leaks
    }

    private fun showItems(category: String) {
        val items = ItemRepository.getItemByCategory(category)

        // Clear any existing views in the grid layout
        binding.gridLayout.removeAllViews()

        items?.forEach { item ->
            val itemCardView = LayoutInflater.from(context)
                .inflate(R.layout.item_card, binding.gridLayout, false) as CardView

            item?.let {
                // Set item details dynamically
                itemCardView.findViewById<TextView>(R.id.itemName).text = item.name
                itemCardView.findViewById<TextView>(R.id.unit).text = item.unit
                itemCardView.findViewById<TextView>(R.id.itemPrice).text = item.priceLow
                itemCardView.findViewById<ImageView>(R.id.itemImage).setImageResource(it.imageResId)

                // Set click listener for the add button
                val addItemButton = itemCardView.findViewById<ConstraintLayout>(R.id.addItem)
                addItemButton.tag = item // Set the item as a tag for easy retrieval

                addItemButton.setOnClickListener { view ->
                    val clickedItem = view.tag as Item
                    sharedViewModel.addItem(clickedItem)
                    Toast.makeText(
                        requireContext(),
                        "${clickedItem.name} added to cart",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                // Handle card click for details
                itemCardView.setOnClickListener {
                    val bundle = Bundle().apply {
                        putString("item", item.name)
                        putInt("imageResId", item.imageResId)
                    }
                    itemDetailsFragment.arguments = bundle
                    parentFragmentManager.beginTransaction()
                        .replace(R.id.rootFl, itemDetailsFragment)
                        .addToBackStack(null)
                        .commit()
                }

                // Add the card to the grid layout
                binding.gridLayout.addView(itemCardView)
            }
        }
    }

}