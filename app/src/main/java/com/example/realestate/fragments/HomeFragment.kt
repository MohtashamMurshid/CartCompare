package com.example.realestate.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.realestate.ItemRepository
import com.example.realestate.Navigation
import com.example.realestate.R
import com.example.realestate.SharedCartViewModel
import com.example.realestate.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val itemDetailsFragment = ItemDetailsFragment()

    private val sharedViewModel: SharedCartViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Map button IDs to item names
        val buttonToItemMap = mapOf(
            R.id.addItem to "Apples",
            R.id.addMango to "Mangoes",
            R.id.addWatermelon to "Watermelon",
            R.id.addStrawberry to "Strawberry",
            R.id.addBanana to "Bananas"
        )

        // Set click listeners for all buttons dynamically
        buttonToItemMap.forEach { (buttonId, itemName) ->
            val button = binding.root.findViewById<View>(buttonId)
            button.setOnClickListener {
                val item = ItemRepository.getItemByName(itemName) // Get item dynamically
                item?.let {
                    sharedViewModel.addItem(it) // Add item to cart
                    Toast.makeText(requireContext(), "${it.name} added to cart", Toast.LENGTH_SHORT).show()
                }
            }
        }

        // List of category buttons
        val categoryBtns = listOf(
            binding.fruitsBtn,
            binding.veggiesBtn,
            binding.meatBtn,
            binding.fishBtn,
            binding.seafoodBtn,
            binding.eggmilkBtn,
            binding.snacksBtn
        )

        categoryBtns.forEach { button ->
            button.setOnClickListener {
                val categoryText = button.contentDescription?.toString() ?: "Unknown"
                Log.d("HomeFragment", "Category clicked: $categoryText") // Log the category

                // Pass the category to MainActivity
                val mainActivity = requireActivity() as? Navigation
                mainActivity?.showAddFragment(categoryText)
                Toast.makeText(requireContext(), "Navigating to $categoryText", Toast.LENGTH_SHORT).show()
            }
        }

        // Fruit card views: Navigate to ItemDetailsFragment
        val fruitItems = listOf(
            binding.appleCard,
            binding.mangoCard,
            binding.watermelonCard,
            binding.strawberryCard,
            binding.bananaCard
        )

        fruitItems.forEach { itemCardView ->
            itemCardView.setOnClickListener {
                val itemName = itemCardView.contentDescription.toString()

                // Fetch item details dynamically
                val selectedItem = ItemRepository.getItemByName(itemName)

                selectedItem?.let { item ->
                    // Bundle to pass item details
                    val bundle = Bundle().apply {
                        putString("item", item.name) // Use correct property name
                        putInt("imageResId", item.imageResId) // Pass correct image resource
                    }
                    itemDetailsFragment.arguments = bundle

                    // Perform navigation to ItemDetailsFragment
                    parentFragmentManager.beginTransaction()
                        .replace(R.id.rootFl, itemDetailsFragment)
                        .addToBackStack("HomeFragment") // Add back stack for navigation
                        .commit()

                    Toast.makeText(requireContext(), "$itemName details shown", Toast.LENGTH_SHORT).show()
                } ?: Toast.makeText(requireContext(), "Item not found!", Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
