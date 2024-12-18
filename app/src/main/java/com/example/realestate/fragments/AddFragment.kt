package com.example.realestate.fragments

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.realestate.ItemRepository
import com.example.realestate.R
import com.example.realestate.databinding.FragmentAddBinding

class AddFragment : Fragment() {

    private val itemDetailsFragment = ItemDetailsFragment()
    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView( //binding process with fragment_add.xml
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) { //now that the binding is done, Im setting up UI interactions and logic here
        super.onViewCreated(view, savedInstanceState) //mandatory

        //setting up colors for selected catogeries
        val textViewList = listOf(
            binding.fruitsTv,
            binding.veggiesTv,
            binding.meatTv,
            binding.fishTv,
            binding.seafoodTv,
            binding.dairyTv,
            binding.snacksTv
        )

        // setting up click listeners for each category
        textViewList.forEach { textView ->
            textView.setOnClickListener {
                // Set all TextViews to the original color
                textViewList.forEach { it.setTextColor(Color.parseColor("#34DC1E"))}

                // Set the clicked TextView's color to the selected color
                textView.setTextColor(Color.parseColor("#007AFF"))

                //clear current category
                binding.gridLayout.removeAllViews()
                //show the selected category
                when(it.id){
                    binding.fruitsTv.id -> showItems("Fruits")
                    binding.veggiesTv.id -> showItems("Veggies")
                    binding.meatTv.id -> showItems("Meat")
                    binding.fishTv.id -> showItems("Fish")
                    binding.seafoodTv.id -> showItems("Seafood")
                    binding.dairyTv.id -> showItems("Eggs & Dairy")
                    binding.snacksTv.id -> showItems("Snacks")
                }

            }
        }

        //handling arguments,
        arguments?.getString("category")?.let{
            showItems(it) //'it' holds string value of either "Fruits"/ "Veggies"/ "Meat"/ etc

            // Set all TextViews to the original color
            textViewList.forEach { it.setTextColor(Color.parseColor("#34DC1E"))}

            // Set blue text color for the selected category using 'it'
            textViewList.find { textView-> it == textView.text }?.setTextColor(Color.parseColor("#007AFF"))
        }

        showItems("Fruits")

    }

    //for cleaning up any references or resources that might cause memory leaks
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Prevent memory leaks
    }

    private fun showItems(category: String){


        //fetching list of items from the category
        ItemRepository.getItemByCategory(category)?.forEach{ item->
            // Inflate the item_card.xml layout for each item
            val itemCardView = LayoutInflater.from(context).inflate(R.layout.item_card, binding.gridLayout, false) as CardView

            item?.let {
                // Fill up the card with item data
                itemCardView.findViewById<TextView>(R.id.itemName).text = item.name
                itemCardView.findViewById<TextView>(R.id.unit).text = item.unit
                itemCardView.findViewById<TextView>(R.id.itemPrice).text = item.priceLow

                // Set the image
                itemCardView.findViewById<ImageView>(R.id.itemImage).setImageResource(it.imageResId)

                //setClickListeners on "+" button
                itemCardView.findViewById<ConstraintLayout>(R.id.addItem).setOnClickListener {
                    Toast.makeText(requireContext(),"${item.name} added to cart",Toast.LENGTH_SHORT).show()
                }

                //setClickListener on whole card
                itemCardView.rootView.setOnClickListener {
                    //bundle is for passing data between fragments
                    val bundle = Bundle()
                    bundle.putString("item", item.name) // Pass item data so that ItemDetails know which item to show
                    itemDetailsFragment.arguments = bundle

                    // Perform the fragment transaction
                    val transaction = requireActivity().supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.rootFl, itemDetailsFragment)
//                    transaction.addToBackStack(null)
                    transaction.commit()
                    Toast.makeText(requireContext(),"${item.name} details shown",Toast.LENGTH_SHORT).show()
                }

                // Add the card to the GridLayout
                binding.gridLayout.addView(itemCardView)
            }
        }

    }


}