package com.example.realestate.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.realestate.Navigation
import com.example.realestate.R
import com.example.realestate.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private val itemDetailsFragment = ItemDetailsFragment()
    private val addFragment = AddFragment()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val categoryBtns = listOf(
            binding.fruitsBtn,
            binding.veggiesBtn,
            binding.meatBtn,
            binding.fishBtn,
            binding.seafoodBtn,
            binding.eggmilkBtn,
            binding.snacksBtn

        )
        val fruitItems = listOf(
            binding.appleCard,
            binding.mangoCard,
            binding.watermelonCard,
            binding.strawberryCard,
            binding.bananaCard
        )




        categoryBtns.forEach { category ->
            category.setOnClickListener {
                // Get a reference to the MainActivity
                val mainActivity = requireActivity() as? Navigation

                // Get the content description of the ImageButton (assuming it holds the category text)
                val categoryText = category.contentDescription.toString()
                // Call the showAddFragment() method on the MainActivity
                mainActivity?.showAddFragment(categoryText)

                Toast.makeText(requireContext(), "Navigating to $categoryText category ", Toast.LENGTH_SHORT).show()
            }
        }
        fruitItems.forEach { itemCardView ->
            itemCardView.setOnClickListener{
                val mainActivity = requireActivity() as? Navigation

                // Get the content description of the ImageButton (assuming it holds the category text)
                val categoryText = itemCardView.contentDescription.toString()
                // Call the showAddFragment() method on the MainActivity
                mainActivity?.showAddFragment(categoryText)

                Toast.makeText(requireContext(), "Navigating to $categoryText category ", Toast.LENGTH_SHORT).show()
                val bundle = Bundle()
                bundle.putString("item", categoryText) // Pass item data so that ItemDetails know which item to show
                itemDetailsFragment.arguments = bundle

                // Perform the fragment transaction
                val transaction = requireActivity().supportFragmentManager.beginTransaction()
                transaction.replace(R.id.rootFl, itemDetailsFragment)
//                    transaction.addToBackStack(null)
                transaction.commit()
                Toast.makeText(requireContext(),"${itemCardView.contentDescription} details shown",Toast.LENGTH_SHORT).show()
            }
        }

    }
}



//                //ignore these comments:
//                //bundle is for passing data between fragments
//                val bundle = Bundle()
//                bundle.putString("category", category.text.toString()) // Pass the category so that AddFragment knows which category to display
//                addFragment.arguments = bundle
//
//                // Perform the fragment transaction
//                val mainActivityFragmentMananger = (requireActivity() as MainActivity).supportFragmentManager // selecting transaction manager of Main Activity
//                val transaction = mainActivityFragmentMananger.beginTransaction()
//                transaction.replace(R.id.fragmentsFl, addFragment)
//                transaction.commit()
//                Toast.makeText(requireContext(),"navigating to ${category.text} category ", Toast.LENGTH_SHORT).show()